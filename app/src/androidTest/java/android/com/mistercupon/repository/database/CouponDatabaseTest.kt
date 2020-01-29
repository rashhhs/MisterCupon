package android.com.mistercupon.repository.database

import android.com.mistercupon.repository.database.daos.CouponDao
import android.com.mistercupon.repository.model.data.Coupon
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class CouponDatabaseTest {
    private lateinit var couponDao: CouponDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,  AppDatabase::class.java).build()
        couponDao = db.couponDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val list:MutableList<Coupon> = ArrayList()
        list.add(Coupon("empty_space",Coupon.HEADER_TYPE))
        list.add(Coupon("as2fcv",Coupon.COUPON_TYPE,"Vegetable ECO Biscuit","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHYbQ6jgaplwvuyB9-SRmO4IfPC4PrfCl6lFzRcRvdLCG1Zovo&s","3 days to finish",false
            ,"-20%","My Best Veggie","This is a product description","Limited to 156 units","Coupon redeemable 1 time","5707984"))
        list.add(Coupon("des3gt",Coupon.COUPON_TYPE,"Activia 0%","https://cdn.hispantv.com/hispanmedia/files/images/thumbnail/20190119/01485682_xl.jpg","3 days to finish",false
            ,"-20%","Danone","This is a product description","Limited to 2500 units","Coupon redeemable 1 time","5707984"))

        couponDao.insertCoupon(list).andThen{
            val coupon = couponDao.getCoupon()
            assertEquals("Vegetable ECO Biscuit",coupon.title)
        }
    }
}