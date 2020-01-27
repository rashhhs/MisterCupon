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

    lateinit var view: CouponViewContract
    lateinit var coupons:LiveData<PagedList<CouponView>>
    var isFirstTime: MutableLiveData<Boolean> = MutableLiveData()

    init {
        initData()
        setData()
    }

    fun initData(){
        Single.just(updateFirstTime(true))
            .delay(1000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterSuccess { updateFirstTime(false) }
            .subscribe()
    }

    fun setData(){
        val couponModel = Coupon()
        coupons = Transformations.switchMap(
            isFirstTime
        ){
            val isFirstTime = isFirstTime.value ?: false
            if(isFirstTime)
                couponModel.getPlaceholders().mapByPage{input ->  input.map { modelToUnitView(it,true) }}.toLiveData(pageSize = 50)
            else
                couponModel.get().mapByPage{input ->  input.map { modelToUnitView(it,false) }}.toLiveData(pageSize = 50)
        }
    }

    fun setCountractView(view:CouponViewContract){
        this.view = view
    }

    fun updateFirstTime(isFrst:Boolean){
        isFirstTime.value = isFrst
    }

    fun modelToUnitView(value: Coupon, isPlaceholder:Boolean): CouponView {
        return CouponView.Builder()
            .setType(value.type)
            .setTitle(if(isPlaceholder)titlePlaceholder() else value.title)
            .setTitleBackground(if(isPlaceholder)view.getBackgroundPlaceholder()else null)
            .setTitleColor(if(isPlaceholder)view.getPlaceholderTextColor() else view.getDefaultTextColor())
            .setDaysToExpire(if(isPlaceholder)timeToExpirePlaceholder() else value.timeToExpire)
            .setDaysToExpireBackground(if(isPlaceholder)view.getBackgroundPlaceholder() else null)
            .setDaysToExpireColor(if(isPlaceholder)view.getPlaceholderTextColor() else view.getDefaultTextColor())
            .setImgUrl(value.img_url)
            .setIsActivated(value.isActivated)
            .build()
    }

    fun titlePlaceholder():String{
        return "Activia with fruits 0%"
    }

    fun timeToExpirePlaceholder():String{
        return "4 days to finalize"
    }
}
