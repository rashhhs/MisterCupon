package android.com.mistercupon.repository

import android.com.mistercupon.repository.database.Database
import android.com.mistercupon.repository.remote.DownloadService
import android.content.Context
import io.reactivex.disposables.CompositeDisposable

class Repository {
    lateinit var database: Database
    lateinit var downloadService: DownloadService

    fun init(context: Context){
        initDatabase(context)
        initDownloadService()
    }

    private fun initDatabase(context: Context){
        database = Database()
        database.init(context)
    }

    private fun initDownloadService(){
        downloadService = DownloadService.instance
        downloadService.downloadContent()
    }

    fun isDatabaseInit():Boolean{
        return (::database.isInitialized)
    }

    companion object{
        val instance = Repository()

        val databaseCompositeDisposable: CompositeDisposable = CompositeDisposable()
    }
}