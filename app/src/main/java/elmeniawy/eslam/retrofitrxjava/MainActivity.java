package elmeniawy.eslam.retrofitrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import elmeniawy.eslam.retrofitrxjava.adapters.CouponsAdapter;
import elmeniawy.eslam.retrofitrxjava.network.model.StoreCoupons;
import elmeniawy.eslam.retrofitrxjava.network.rest.ApiClient;
import elmeniawy.eslam.retrofitrxjava.network.rest.ApiInterface;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private CompositeDisposable compositeDisposable;
    private Retrofit retrofit;

    @BindView(R.id.recyclerViewCoupon)
    RecyclerView recyclerViewCoupon;

    @BindView(R.id.textViewStoreName)
    TextView textViewStoreName;

    @BindView(R.id.textViewCouponCount)
    TextView textViewCouponCount;

    @BindView(R.id.textViewMaxCashback)
    TextView textViewMaxCashback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //
        // Initialize disposable.
        //

        compositeDisposable = new CompositeDisposable();

        //
        // Set layout manager for recycler view.
        //

        RecyclerView.LayoutManager couponLayoutManager = new LinearLayoutManager(this);
        recyclerViewCoupon.setLayoutManager(couponLayoutManager);

        //
        // Initialize Retrofit.
        //

        retrofit = ApiClient.getClient();
    }

    @Override
    protected void onDestroy() {
        //
        // Dispose subscriptions.
        //

        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }

        super.onDestroy();
    }

    @OnClick(R.id.buttonTopStore)
    void showCouponsTopStore() {
        getStoreCouponData();
    }

    @OnClick(R.id.buttonCoupons)
    void showCoupons() {
        getCouponData();
    }

    /**
     * Two Retrofit service calls execute parallel using RxJava.
     */
    private void getStoreCouponData() {
        //
        // First it creates an observable which emits retrofit service class.
        // To leave current main thread, we need to use subscribeOn which subscribes the observable on computation thread.
        // FlatMap is used to apply function on the item emitted by previous observable.
        // Function makes two rest service calls using the give retrofit object for defined api interface.
        // These two calls run parallel that is why subscribeOn is used on each of them.
        // Since these two api call return same object, they are joined using concatArray operator.
        // Finally consumer observes on android main thread.
        //

        compositeDisposable.add(Observable.just(retrofit.create(ApiInterface.class))
                .subscribeOn(Schedulers.computation())
                .flatMap(s -> {
                    Observable<StoreCoupons> couponsObservable = s
                            .getCoupons("topcoupons")
                            .subscribeOn(Schedulers.io());

                    Observable<StoreCoupons> storeInfoObservable = s
                            .getStoreInfo()
                            .subscribeOn(Schedulers.io());

                    //noinspection unchecked
                    return Observable.concatArray(couponsObservable, storeInfoObservable);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError));
    }

    /**
     * Single api call using Retrofit and RxJava.
     */
    private void getCouponData() {
        compositeDisposable.add(retrofit.create(ApiInterface.class)
                .getCoupons("topcoupons")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError));
    }

    private void handleResults(StoreCoupons storeCoupons) {
        if (storeCoupons.getCoupons() != null) {
            CouponsAdapter ca = new CouponsAdapter(storeCoupons.getCoupons(), MainActivity.this);
            recyclerViewCoupon.setAdapter(ca);
        } else {
            textViewStoreName.setText(storeCoupons.getStore());
            textViewCouponCount.setText(storeCoupons.getTotalCoupons());
            textViewMaxCashback.setText(storeCoupons.getMaxCashback());
        }
    }

    private void handleError(Throwable t) {
        Log.e("Observer", t.toString());
        Toast.makeText(this, "ERROR IN GETTING COUPONS", Toast.LENGTH_SHORT).show();
    }
}
