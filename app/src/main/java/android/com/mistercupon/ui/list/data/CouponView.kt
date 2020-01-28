package android.com.mistercupon.ui.list.data

import android.graphics.drawable.Drawable
import java.io.Serializable

data class CouponView(
    var id:String = ""
):Serializable{
    var type:String = ""
    var imgUrl:String = ""
    var img_background:Drawable? = null
    var brand:String = ""
    var title:String = ""
    var title_background: Drawable? = null
    var title_color: Int = 0
    var isActivated: Boolean = false
    var daysToExpire: String = ""
    var daysToExpire_background: Drawable? = null
    var daysToExpire_color: Int = 0
    var discount:String = ""
    var productDescription:String = ""
    var limitationUnits:String = ""
    var couponTimes:String = ""
    var productCode:String = ""
    var activatedButtonActive:Int = 0

    open class Builder{
        var type:String = ""
        var imgUrl:String = ""
        var img_background:Drawable? = null
        var brand:String = ""
        var title:String = ""
        var title_background: Drawable? = null
        var title_color: Int = 0
        var isActivated: Boolean = false
        var daysToExpire: String = ""
        var daysToExpire_background: Drawable? = null
        var daysToExpire_color: Int = 0
        var discount:String = ""
        var productDescription:String = ""
        var limitationUnits:String = ""
        var couponTimes:String = ""
        var productCode:String = ""
        var activatedButtonActive:Int = 0

        fun setType(type:String) = apply { this.type = type }
        fun setBrand(brand:String) = apply { this.brand = brand }
        fun setTitle(title:String) = apply { this.title = title }
        fun setTitleBackground(draw: Drawable?) = apply { this.title_background = draw }
        fun setTitleColor(color:Int) = apply { this.title_color = color }
        fun setImgUrl(url:String) = apply { this.imgUrl = url }
        fun setImageUrlBackgorund(draw: Drawable?) = apply { this.img_background = draw }
        fun setIsActivated(isActivated:Boolean) = apply { this.isActivated = isActivated }
        fun setDaysToExpire(daysToExpire:String) = apply { this.daysToExpire = daysToExpire }
        fun setDaysToExpireBackground(draw:Drawable?) = apply { this.daysToExpire_background = draw }
        fun setDaysToExpireColor(color:Int) = apply { this.daysToExpire_color = color }
        fun setDiscount(dis:String) = apply { this.discount = dis }
        fun setProductDescription(des:String) = apply { this.productDescription = des }
        fun setLimitationUnits(units:String) = apply { this.limitationUnits = units }
        fun setCouponTimes(times:String) = apply { this.couponTimes = times }
        fun setProductCode(code:String) = apply { this.productCode = code }
        fun setActivatedButton(visibility:Int) = apply { this.activatedButtonActive = visibility }

        fun build():CouponView{
            val couponView = CouponView(title)
            couponView.type = type
            couponView.brand = brand
            couponView.title = title
            couponView.title_background = title_background
            couponView.title_color = title_color
            couponView.imgUrl = imgUrl
            couponView.img_background = img_background
            couponView.isActivated = isActivated
            couponView.daysToExpire = daysToExpire
            couponView.daysToExpire_background = daysToExpire_background
            couponView.daysToExpire_color = daysToExpire_color
            couponView.discount = discount
            couponView.productDescription = productDescription
            couponView.limitationUnits = limitationUnits
            couponView.couponTimes = couponTimes
            couponView.productCode = productCode
            couponView.activatedButtonActive = activatedButtonActive
            return couponView
        }
    }
}