package android.com.mistercupon.repository.remote

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DownloadService {
    private val baseURL = "https://github.com/rashhhs/MisterCupon/tree/master/api_fake/"
    private var interface_service: DownloadServiceInterface
    private var disposable:CompositeDisposable

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        interface_service = retrofit.create(DownloadServiceInterface::class.java)
        disposable = CompositeDisposable()
    }

    companion object{
        val instance = DownloadService()
    }

    fun downloadContent(){
        val downloadCouponList = DownlaodCouponList(interface_service)
        downloadCouponList.download()
    }
}