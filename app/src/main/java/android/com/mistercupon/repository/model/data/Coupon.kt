package android.com.mistercupon.repository.model.data

import android.com.mistercupon.repository.Repository
import android.com.mistercupon.repository.model.DomainLayer
import android.com.mistercupon.repository.model.database.Database
import android.com.mistercupon.ui.list.data.CouponListDataSourceFactory
import android.com.mistercupon.ui.list.data.CouponView
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.toLiveData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import io.reactivex.schedulers.Schedulers

@Entity(tableName = "coupons")
class Coupon (
    @SerializedName("id")
    @field:ColumnInfo(name = "id")
    @PrimaryKey
    var id:String = ""
):DomainLayer<Coupon,CouponView>{
    @SerializedName("image_url")
    @field:ColumnInfo(name = "image_url")
    var img_url:String = ""
    @SerializedName("title")
    @field:ColumnInfo(name = "title")
    var title:String = ""
    @SerializedName("time_to_expire")
    @field:ColumnInfo(name = "time_to_expire")
    var timeToExpire = ""
    @SerializedName("is_activated")
    @field:ColumnInfo(name = "is_activated")
    var isActivated:Boolean = false

    var type:String = COUPON_TYPE

    companion object{
        const val COUPON_TYPE = "coupon"
        const val HEADER_TYPE = "header"
    }

    constructor(id:String,type:String):this(id){
        this.id = id
        this.type = type
    }

    override fun getDatabase(): Database {
        return Repository.instance.database
    }

    override fun insert(values: List<Coupon>) {
        getDatabase().couponDao.insertCoupon(values)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun get(): DataSource.Factory<Int,Coupon> {
       // return getDatabase().couponDao.getCoupons().mapByPage { input -> input.map { modelToUnitView(it) } }.toLiveData(pageSize = 50)
        val source = CouponListDataSourceFactory()
        return source
    }

    override fun getPlaceholders(): DataSource.Factory<Int,Coupon>{
        val source = CouponListDataSourceFactory()
        return source
    }
}