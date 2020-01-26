package android.com.mistercupon.repository

import android.com.mistercupon.repository.model.database.Database
import android.content.Context
import io.reactivex.disposables.CompositeDisposable

class Repository {
    lateinit var database: Database
    //lateinit var downloadService: DownloadService

    fun init(context: Context){
        initDatabase(context)
        //initDownloadService()
    }

    private fun initDatabase(context: Context){
        database = Database()
        database.init(context)
    }

    /*private fun initDownloadService(){
        downloadService = DownloadService.instance
        downloadService.donwloadVersions()
    }*/

    companion object{
        val instance = Repository()

        val databaseCompositeDisposable: CompositeDisposable = CompositeDisposable()
    }
}