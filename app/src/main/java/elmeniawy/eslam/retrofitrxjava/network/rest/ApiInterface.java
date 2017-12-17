package elmeniawy.eslam.retrofitrxjava.network.rest;

import elmeniawy.eslam.retrofitrxjava.network.model.StoreCoupons;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * ApiInterface
 * <p>
 * Created by Eslam El-Meniawy on 17-Dec-2017.
 * CITC - Mansoura University
 */

public interface ApiInterface {
    @GET("coupons")
    Observable<StoreCoupons> getCoupons(@Query("status") String status);

    @GET("storeOffers")
    Observable<StoreCoupons> getStoreInfo();
}
