package android.com.mistercupon.ui.detail
import android.com.mistercupon.R
import android.com.mistercupon.repository.model.data.Coupon
import android.com.mistercupon.ui.list.data.CouponView
import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Before
import org.junit.Test

class CouponDetailFragmentTest {

    lateinit var fragment: CouponDetailFragment

    @Before
    fun setUp(){
        fragment =
            CouponDetailFragment.newInstance()

    }

    fun getCouponView():CouponView{
        val value= Coupon("as2fcv",
            Coupon.COUPON_TYPE,"Vegetable ECO Biscuit","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHYbQ6jgaplwvuyB9-SRmO4IfPC4PrfCl6lFzRcRvdLCG1Zovo&s","3 days to finish",true
            ,"-20%","My Best Veggie","This is a product description","Limited to 156 units","Coupon redeemable 1 time","5707984")

        return CouponView.Builder()
            .setType(value.type)
            .setBrand(value.brand)
            .setTitle(value.title)
            .setDaysToExpire(value.timeToExpire)
            .setImgUrl(value.img_url)
            .setIsActivated(value.isActivated)
            .setDiscount(value.discount)
            .setLimitationUnits(value.limitationUnits)
            .setCouponTimes(value.couponTimes)
            .setProductDescription(value.productDescription)
            .setProductCode(value.productCode)
            .build()
    }

    /*
        Checks that all the coupon's content is setting up correctly
     */
    @Test
    fun settingContent(){
        val fragmentArgs = Bundle().apply {
            putSerializable("coupon", getCouponView())
        }

        val scenario = launchFragmentInContainer(fragmentArgs,R.style.Theme_AppCompat){
            fragment
        }

            onView(withId(R.id.discount_text)).check(matches(withText("-20%")))
            onView(withId(R.id.brand_text)).check(matches(withText("My Best Veggie")))
            onView(withId(R.id.product_text)).check(matches(withText("Vegetable ECO Biscuit")))
            onView(withId(R.id.description_text)).check(matches(withText("This is a product description")))
            onView(withId(R.id.timeToExpire_text)).check(matches(withText("3 days to finish")))
            onView(withId(R.id.limitation_title)).check(matches(withText("Limited to 156 units")))
            onView(withId(R.id.product_code)).check(matches(withText("5707984")))

            onView(withId(R.id.isActivated_button)).check(matches(isChecked()))
    }
}