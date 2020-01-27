package android.com.mistercupon.ui.list.data

import android.graphics.drawable.Drawable

data class CouponView(
    var id:String = ""
){
    var type:String = ""
    var imgUrl:String = ""
    var title:String = ""
    var title_background: Drawable? = null
    var title_color: Int = 0
    var isActivated: Boolean = false
    var daysToExpire: String = ""
    var daysToExpire_background: Drawable? = null
    var daysToExpire_color: Int = 0

    open class Builder{
        var type:String = ""
        var imgUrl:String = ""
        var title:String = ""
        var title_background: Drawable? = null
        var title_color: Int = 0
        var isActivated: Boolean = false
        var daysToExpire: String = ""
        var daysToExpire_background: Drawable? = null
        var daysToExpire_color: Int = 0

        fun setType(type:String) = apply { this.type = type }
        fun setTitle(title:String) = apply { this.title = title }
        fun setTitleBackground(draw: Drawable?) = apply { this.title_background = draw }
        fun setTitleColor(color:Int) = apply { this.title_color = color }
        fun setImgUrl(url:String) = apply { this.imgUrl = url }
        fun setIsActivated(isActivated:Boolean) = apply { this.isActivated = isActivated }
        fun setDaysToExpire(daysToExpire:String) = apply { this.daysToExpire = daysToExpire }
        fun setDaysToExpireBackground(draw:Drawable?) = apply { this.daysToExpire_background = draw }
        fun setDaysToExpireColor(color:Int) = apply { this.daysToExpire_color = color }

        fun build():CouponView{
            val couponView = CouponView(title)
            couponView.type = type
            couponView.title = title
            couponView.title_background = title_background
            couponView.title_color = title_color
            couponView.imgUrl = imgUrl
            couponView.isActivated = isActivated
            couponView.daysToExpire = daysToExpire
            couponView.daysToExpire_background = daysToExpire_background
            couponView.daysToExpire_color = daysToExpire_color
            return couponView
        }
    }
}