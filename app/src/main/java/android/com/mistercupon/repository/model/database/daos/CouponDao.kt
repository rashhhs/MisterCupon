package android.com.mistercupon.repository.model.database.daos

import android.com.mistercupon.repository.model.data.Coupon
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable

@Dao
interface CouponDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoupon(coupons:List<Coupon>):Completable

    @Query("DELETE FROM coupons")
    fun deleteCoupons()

    @Query("SELECT * FROM coupons ORDER BY title")
    fun getCoupons():DataSource.Factory<Int,Coupon>
}