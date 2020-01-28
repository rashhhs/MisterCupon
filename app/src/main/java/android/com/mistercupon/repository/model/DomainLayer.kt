package android.com.mistercupon.repository.model

import android.com.mistercupon.repository.database.Database
import androidx.paging.DataSource

interface DomainLayer<T,Z> {
    fun getDatabase():Database
    fun insert(values:List<T>)
    fun get(): DataSource.Factory<Int,T>
    fun getPlaceholders():DataSource.Factory<Int,T>
}