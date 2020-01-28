package android.com.mistercupon.repository.database.daos

import android.com.mistercupon.repository.model.data.Coupon
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CouponDao {
    //(R):On a real project without a fake API service, should be REPLACE instead of ABORT
    //The insert method is using ABORT because with REPLACE, the database would loose the coupons activations
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertCoupon(coupons:List<Coupon>):Completable

    @Query("DELETE FROM coupons")
    fun deleteCoupons()

    @Query("SELECT * FROM coupons ORDER BY title")
    fun getCoupons():DataSource.Factory<Int,Coupon>

    @Query("UPDATE coupons SET is_activated = :isActivated WHERE title = :title ")
    fun updateCouponActivation(title:String, isActivated:Boolean):Completable

    @Query("SELECT COUNT(*) FROM coupons WHERE is_activated = 1")
    fun getActivateCoupons(): LiveData<Int>

}