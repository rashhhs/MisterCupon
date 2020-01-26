package android.com.mistercupon.ui.list.data

import android.com.mistercupon.repository.model.data.Coupon
import androidx.paging.PositionalDataSource

class CouponListDataSource: PositionalDataSource<Coupon>() {
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Coupon>) {
        val items = getListOfPlaceholders()
        callback.onResult(items)
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Coupon>) {
        val items = getListOfPlaceholders()
        callback.onResult(items,0,4)
    }

    fun getListOfPlaceholders():List<Coupon>{
        return listOf(Coupon("a"),Coupon("b"), Coupon("c"), Coupon("d"))
    }
}