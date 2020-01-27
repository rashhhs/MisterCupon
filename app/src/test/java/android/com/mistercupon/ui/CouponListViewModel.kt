package android.com.mistercupon.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

class CouponListViewModel {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel:CouponListViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = CouponListViewModel()
    }

    /*
        Check that the first time the user enters on the coupon list, the screen's model returns the placeholders.
        After 1 seconds more or less returns the real content
     */
    fun couponListStates(){

    }

    /*
        If the users activates the coupon should be send to the model in order to populate the order to the API service
     */
    fun activateCoupon(){

    }
}