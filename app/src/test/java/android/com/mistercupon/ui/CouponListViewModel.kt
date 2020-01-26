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
        Check that the first time the user enters on the coupon list screen the model return the placeholders.
        And the following states the model always returns the real list of content
     */
    fun couponListStates(){

    }

    //Should be necessary to check if viewContract on viewModel is null what happens
}