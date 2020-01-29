package android.com.mistercupon.ui.list

import android.com.mistercupon.ui.list.data.CouponView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
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
    lateinit var observer1:Observer<Boolean>

    lateinit var viewModel: CouponListViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = CouponListViewModel()
    }

    /*
        Test to check that with false show placeholders if not shows the content
     */
    @Test
    fun couponListStates(){
        val spy = spy(viewModel)

        spy.isFirstTime.observeForever(observer1)
        //spy.coupons.observeForever(observer)

        //spy.setData()
        spy.isFirstTime.value = true
        spy.isFirstTime.value = false

        verify(spy, times(3)).getPlaceholders()
        verify(spy,times(1)).getData()
    }

    /*
        If the users activates the coupon should be send to the model in order to populate the order to the API service
     */
    fun activateCoupon(){

    }
}