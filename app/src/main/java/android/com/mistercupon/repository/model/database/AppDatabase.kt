package android.com.mistercupon.repository.model.database

import android.com.mistercupon.repository.model.data.Coupon
import android.com.mistercupon.repository.model.database.daos.CouponDao
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Coupon::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun couponDao():CouponDao
}