package android.com.mistercupon.ui.utils

import android.view.View

object AnimationUtils {

    fun animateXView(view: View, position:Float){
        view.animate().x(position)
    }
}