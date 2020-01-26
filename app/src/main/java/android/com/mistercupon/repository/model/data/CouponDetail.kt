package android.com.mistercupon.repository.model.data

import com.google.gson.annotations.SerializedName

class CouponDetail {

    @SerializedName("img_url")
    var imgUrl:String = ""

    @SerializedName("discount")
    var discount:String= ""

    @SerializedName("brand")
    var brand:String = ""

    @SerializedName("product_title")
    var productTitle: String = ""

    @SerializedName("product_description")
    var productDescription:String = ""

    @SerializedName("days_to_expire")
    var daysToExpire:String = ""

    @SerializedName("limitation_units")
    var limitationUnits:String = ""

    @SerializedName("coupon_times")
    var couponTimes:String = ""

    @SerializedName("product_code")
    var productCode:String = ""
}