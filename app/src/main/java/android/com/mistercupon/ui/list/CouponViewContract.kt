package android.com.mistercupon.ui.list

import android.graphics.drawable.Drawable

interface CouponViewContract {
    fun getBackgroundPlaceholder(): Drawable?
    fun getPlaceholderTextColor(): Int
    fun getDefaultTextColor():Int
    fun getViewVisible():Int
    fun getViewGone():Int
}