package elmeniawy.eslam.retrofitrxjava.network.model;

import java.util.List;

/**
 * StoreCoupons
 * <p>
 * Created by Eslam El-Meniawy on 17-Dec-2017.
 * CITC - Mansoura University
 */

@SuppressWarnings("unused")
public class StoreCoupons {
    private String store;
    private String totalCoupons ;
    private String maxCashback;
    private List<Coupon> coupons;

    //
    // Setters.
    //

    public void setStore(String store) {
        this.store = store;
    }

    public void setTotalCoupons(String totalCoupons) {
        this.totalCoupons = totalCoupons;
    }

    public void setMaxCashback(String maxCashback) {
        this.maxCashback = maxCashback;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }


    //
    // Getters.
    //

    public String getStore() {
        return store;
    }

    public String getTotalCoupons() {
        return totalCoupons;
    }

    public String getMaxCashback() {
        return maxCashback;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }
}
