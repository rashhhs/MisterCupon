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
        Test coupons list getting correctly content (placeholders or real content if its ready)
     */
    @Test
    fun couponListStates(){
        val spy = spy(viewModel)
        val source = CouponListDataSourceFactory()
        var a = 0

        //whenever(spy.modelToUnitView(Coupon(eq("")), any())).thenReturn(CouponView.Builder().build())
        //whenever(spy.view).thenReturn(view)
        //whenever(viewModel.getPlaceholdersData()).thenReturn(liveData)

        whenever(spy.couponInstance).thenReturn(couponModel)
        whenever(spy.couponInstance.getPlaceholders()).thenReturn(source)
        whenever(spy.getPlaceholdersData()).thenReturn(liveData)

        spy.coupons.observeForever(observer)
        spy.isFirstTime.observeForever(observer1)

        spy.updateFirstTime(CouponListViewModel.coupons_states.PLACEHOLDER)
        //spy.isFirstTime.value = true
        //spy.isFirstTime.value = false

        verify(spy, times(3)).getPlaceholdersData()
        verify(spy,times(1)).getCouponsData()
    }
}