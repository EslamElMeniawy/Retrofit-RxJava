package elmeniawy.eslam.retrofitrxjava.network.model;

/**
 * Coupon
 * <p>
 * Created by Eslam El-Meniawy on 17-Dec-2017.
 * CITC - Mansoura University
 */

@SuppressWarnings("unused")
public class Coupon {
    private String store;
    private String coupon;
    private String expiryDate;
    private String couponCode;
    private String category;

    //
    // Setters.
    //


    public void setStore(String store) {
        this.store = store;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    //
    // Getters.
    //


    public String getStore() {
        return store;
    }

    public String getCoupon() {
        return coupon;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public String getCategory() {
        return category;
    }
}
