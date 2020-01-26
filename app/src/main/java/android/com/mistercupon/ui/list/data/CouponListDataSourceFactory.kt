package android.com.mistercupon.ui.list.data

import android.com.mistercupon.repository.model.data.Coupon
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

class CouponListDataSourceFactory: DataSource.Factory<Int,Coupon>() {
    val sourceLiveData = MutableLiveData<DataSource<Int,Coupon>>()
    override fun create(): DataSource<Int, Coupon> {
        val source = CouponListDataSource()
        sourceLiveData.postValue(source)
        return source
    }
}