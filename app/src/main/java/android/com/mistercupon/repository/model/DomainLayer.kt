package android.com.mistercupon.repository.model

import android.com.mistercupon.repository.model.database.Database
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList

interface DomainLayer<T,Z> {
    fun getDatabase():Database
    fun insert(values:List<T>)
    fun get(): DataSource.Factory<Int,T>
    fun getPlaceholders():DataSource.Factory<Int,T>
}