package android.com.mistercupon.ui.list

import android.com.mistercupon.repository.model.data.Coupon
import android.com.mistercupon.ui.list.data.CouponView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CouponListViewModel : ViewModel() {

    enum class coupons_states {PLACEHOLDER,DEFAULT}
    lateinit var view: CouponViewContract
    var coupons:LiveData<PagedList<CouponView>>
    var isFirstTime: MutableLiveData<coupons_states> = MutableLiveData()
    var couponsActive: LiveData<Int> = MutableLiveData()

    var couponInstance: Coupon = Coupon()

    init {
        coupons = setData()
        couponsActive = getActiveCoupons()
    }

    fun start(){
        initData()
        setData()
    }

    fun initData(){
        Single.just(updateFirstTime(coupons_states.PLACEHOLDER))
            .delay(1000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterSuccess { updateFirstTime(coupons_states.DEFAULT) }
            .subscribe()
    }

    fun setData():LiveData<PagedList<CouponView>>{
        return Transformations.switchMap(
            isFirstTime
        ){
            when(isFirstTime.value){
                coupons_states.PLACEHOLDER -> getPlaceholdersData()
                else -> getCouponsData()
            }
        }
    }
    
    fun getPlaceholdersData():LiveData<PagedList<CouponView>>{
        return couponInstance.getPlaceholders().mapByPage{input ->  input.map { modelToUnitView(it,true) }}.toLiveData(pageSize = 50)
    }

    fun getCouponsData():LiveData<PagedList<CouponView>>{
        return couponInstance.get().mapByPage{input ->  input.map { modelToUnitView(it,false) }}.toLiveData(pageSize = 50)
    }

    fun getActiveCoupons():LiveData<Int>{
        return couponInstance.getActiveCoupons()
    }

    fun setCountractView(view:CouponViewContract){
        this.view = view
    }

    fun updateFirstTime(isFrst:coupons_states){
        isFirstTime.value = isFrst
    }

    fun modelToUnitView(value: Coupon, isPlaceholder:Boolean): CouponView {
        return CouponView.Builder()
            .setType(value.type)
            .setBrand(value.brand)
            .setTitle(if(isPlaceholder)titlePlaceholder() else value.title)
            .setTitleBackground(if(isPlaceholder)view.getBackgroundPlaceholder()else null)
            .setTitleColor(if(isPlaceholder)view.getPlaceholderTextColor() else view.getDefaultTextColor())
            .setDaysToExpire(if(isPlaceholder)timeToExpirePlaceholder() else value.timeToExpire)
            .setDaysToExpireBackground(if(isPlaceholder)view.getBackgroundPlaceholder() else null)
            .setDaysToExpireColor(if(isPlaceholder)view.getPlaceholderTextColor() else view.getDefaultTextColor())
            .setImgUrl(value.img_url)
            .setImageUrlBackgorund(if(isPlaceholder)view.getBackgroundPlaceholder() else null)
            .setIsActivated(value.isActivated)
            .setDiscount(value.discount)
            .setLimitationUnits(value.limitationUnits)
            .setCouponTimes(value.couponTimes)
            .setProductDescription(value.productDescription)
            .setProductCode(value.productCode)
            .setActivatedButton(if(isPlaceholder)view.getViewVisible() else view.getViewGone())
            .build()
    }

    fun titlePlaceholder():String{
        return "Activia with fruits 0%"
    }

    fun timeToExpirePlaceholder():String{
        return "4 days to finalize"
    }

    fun setCoupon(instance:Coupon){
        couponInstance = instance
    }
}
