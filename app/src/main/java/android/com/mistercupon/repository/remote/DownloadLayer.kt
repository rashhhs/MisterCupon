package android.com.mistercupon.repository.remote

interface DownloadLayer<T> {
    fun download()
    fun store(values:List<T>)
}