package android.com.mistercupon.ui.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object ImageUtils {

    fun loadImage(url: String, placeholder: Int, view: ImageView, context: Context) {
        Glide.with(context)
            .load(url)
            .placeholder(placeholder)
            .into(view)
    }

    fun loadImage(url:String, placeholder: Int, error: Int, view: ImageView, context: Context){
        Glide.with(context)
            .load(url)
            .placeholder(placeholder)
            .error(error)
            .into(view)
    }

    fun loadLocalImage(image: Int, view: ImageView, context: Context) {
        Glide.with(context)
            .load(image)
            .into(view)
    }

    fun loadImageCircular(url: String, placeholder: Int, view: ImageView, context: Context) {
        Glide.with(context)
            .load(url)
            .placeholder(placeholder)
            .apply(RequestOptions.circleCropTransform())
            .into(view)
    }
}