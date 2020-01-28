package android.com.mistercupon.repository.remote

import android.com.mistercupon.repository.model.data.Coupon
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DownloadServiceInterface {

    @GET("coupon/coupon_list.json")
    fun getCouponList(): Single<List<Coupon>>
}