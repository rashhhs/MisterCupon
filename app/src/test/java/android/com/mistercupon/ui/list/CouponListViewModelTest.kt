package android.com.mistercupon.ui.list

import android.com.mistercupon.repository.model.data.Coupon
import android.com.mistercupon.ui.list.data.CouponListDataSourceFactory
import android.com.mistercupon.ui.list.data.CouponView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.*
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CouponListViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<PagedList<CouponView>>
    @Mock
    lateinit var observer1:Observer<CouponListViewModel.coupons_states>
    @Mock
    lateinit var couponModel:Coupon
    @Mock
    lateinit var factory:DataSource.Factory<Int,Coupon>
    @Mock
    lateinit var liveData: LiveData<PagedList<CouponView>>
    @Mock
    lateinit var view:CouponViewContract

    lateinit var viewModel: CouponListViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = CouponListViewModel()
        viewModel.setCoupon(couponModel)
        viewModel.setCountractView(view)
    }

    /*
        Test that depends what the screen wants to show. The viewModel ask for a different DataSource.Factory to the model
     */
    @Test
    fun couponListStates(){
        val spy = spy(viewModel)
        val source = CouponListDataSourceFactory()
        var placeholderCounts = 0
        var defaultCounts = 0

        whenever(spy.couponInstance).thenReturn(couponModel)
        whenever(spy.couponInstance.getPlaceholders()).doAnswer {
            placeholderCounts++
            source
        }
        whenever(spy.couponInstance.get()).doAnswer {
            defaultCounts++
            source
        }

        spy.coupons.observeForever(observer)
        spy.isFirstTime.observeForever(observer1)

        spy.updateFirstTime(CouponListViewModel.coupons_states.PLACEHOLDER)
        spy.updateFirstTime(CouponListViewModel.coupons_states.DEFAULT)
        spy.updateFirstTime(CouponListViewModel.coupons_states.DEFAULT)

        assertEquals(1,placeholderCounts)
        assertEquals(2,defaultCounts)
    }
}