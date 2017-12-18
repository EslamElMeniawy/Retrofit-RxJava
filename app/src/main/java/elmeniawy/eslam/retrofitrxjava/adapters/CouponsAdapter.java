package elmeniawy.eslam.retrofitrxjava.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import elmeniawy.eslam.retrofitrxjava.R;
import elmeniawy.eslam.retrofitrxjava.network.model.Coupon;

/**
 * CouponsAdapter
 * <p>
 * Created by Eslam El-Meniawy on 18-Dec-2017.
 * CITC - Mansoura University
 */

public class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.ViewHolder> {
    private List<Coupon> couponList;
    private Context context;

    public CouponsAdapter(List<Coupon> cpnList, Context ctx) {
        couponList = cpnList;
        context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Coupon coupon = couponList.get(position);
        holder.tvStore.setText(coupon.getStore());
        holder.tvCoupon.setText(coupon.getCoupon());
        holder.tvExpiry.setText(coupon.getExpiryDate());
        holder.tvCouponCode.setText(coupon.getCouponCode());
    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewStore)
        TextView tvStore;

        @BindView(R.id.textViewCoupon)
        TextView tvCoupon;

        @BindView(R.id.textViewExpiry)
        TextView tvExpiry;

        @BindView(R.id.textViewCouponCode)
        TextView tvCouponCode;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.cardViewCoupon)
        void cardClicked() {
            Toast.makeText(context, "You chose coupon " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
        }
    }
}
