package android.com.mistercupon.ui.list

import android.com.mistercupon.R
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Test

//(R): Known issue: There seems there is a bug with the Expresso and how detectes the title text and the textView child inside
//Is necessary more time to check what is going on and if it is possible to resolve this
class CouponListFragmentTest {
    lateinit var fragment:CouponListFragment

    @Before
    fun setUp(){
        fragment = CouponListFragment.newInstance()
    }


    @Test
    fun activeCoupon_collapsed(){
        val scenario = launchFragmentInContainer(Bundle(), R.style.Theme_AppCompat){
            fragment
        }

        val num = 76

        scenario.onFragment { fragment ->
            fragment.updateCouponsActive(num)
            fragment.mAppBarLayout.setExpanded(false)
        }

        onView(withId(R.id.couponsActiveTextView)).check(matches(withText("$num coupons activated")))
        onView(withId(R.id.couponsActiveTextView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(
            allOf(
                instanceOf(TextView::class.java),
                withParent(withId(R.id.collapsingToolbar))
            )
        )
            .check(matches(withText("$num coupons activated")))
    }

    @Test
    fun activeCoupon_expanded(){
        val scenario = launchFragmentInContainer(Bundle(), R.style.Theme_AppCompat){
            fragment
        }
        val num = 3

        scenario.onFragment { fragment ->
            fragment.updateCouponsActive(num)
            fragment.mAppBarLayout.setExpanded(true)
        }

        onView(withId(R.id.couponsActiveTextView)).check(matches(withText("$num coupons activated")))
        onView(withId(R.id.couponsActiveTextView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        //(R): The view get it is 'couponsActiveTextView' and should be the title of the toolbar
        onView(
            allOf(
                instanceOf(TextView::class.java),
                withParent(withId(R.id.collapsingToolbar))
            )
        )
            .check(matches(withText("Your coupons")))
    }
}