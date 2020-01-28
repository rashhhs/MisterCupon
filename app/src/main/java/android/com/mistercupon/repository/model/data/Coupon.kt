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
    @SerializedName("discount")
    @field:ColumnInfo(name = "discount")
    var discount:String = ""
    @SerializedName("brand")
    @field:ColumnInfo(name = "brand")
    var brand:String = ""
    @SerializedName("product_description")
    @field:ColumnInfo(name="product_description")
    var productDescription:String = ""
    @SerializedName("limitation units")
    @field:ColumnInfo(name="limitation_units")
    var limitationUnits:String = ""
    @SerializedName("coupon_times")
    @field:ColumnInfo(name = "coupon_times")
    var couponTimes:String = ""
    @SerializedName("product_code")
    @field:ColumnInfo(name = "product_code")
    var productCode:String =""

    var type:String = COUPON_TYPE

    companion object{
        const val COUPON_TYPE = "coupon"
        const val HEADER_TYPE = "header"
    }

    constructor(id:String,type:String):this(id){
        this.id = id
        this.type = type
    }

    constructor(id:String,type:String,title:String,url:String,timeToExpire:String,isActivated:Boolean,
                discount:String,brand:String,des:String,limit:String,times:String,code:String):this(id){
        this.id = id
        this.type = type
        this.img_url = url
        this.title = title
        this.timeToExpire = timeToExpire
        this.isActivated = isActivated
        this.discount = discount
        this.brand = brand
        this.productDescription = des
        this.limitationUnits = limit
        this.couponTimes = times
        this.productCode = code
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
       return getDatabase().couponDao.getCoupons()
        //val source = CouponListDataSourceFactory()
       // return source
    }

    override fun getPlaceholders(): DataSource.Factory<Int,Coupon>{
        val source = CouponListDataSourceFactory()
        return source
    }
}