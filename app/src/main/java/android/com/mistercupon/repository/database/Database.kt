package android.com.mistercupon.repository.database

import android.com.mistercupon.repository.database.daos.CouponDao
import android.content.Context
import androidx.room.Room

class Database {
    lateinit var couponDao: CouponDao

    fun init(context: Context){
        val roomDB = Room.databaseBuilder(context, AppDatabase::class.java, "mister-coupon-instance")
            .fallbackToDestructiveMigration()
            .build()

        couponDao = roomDB.couponDao()
    }
}