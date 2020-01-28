package android.com.mistercupon.repository.remote

import android.com.mistercupon.repository.Repository
import android.com.mistercupon.repository.model.data.Coupon
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class DownlaodCouponList(val serviceInterface: DownloadServiceInterface):DownloadLayer<Coupon> {
    override fun download() {
        executeObservable(serviceInterface.getCouponList())
    }

    //(R): FAKE DOWNLOAD
    fun executeObservable(observable: Single<List<Coupon>>){
        Repository.databaseCompositeDisposable.add(observable
            .subscribeOn(Schedulers.newThread())
            .subscribe { t1, t2 ->
                if(t1 != null)
                    store(t1)
                else
                    store(createList())
            })
    }

    override fun store(values: List<Coupon>) {
        val coupon = Coupon()
        coupon.insert(values)
    }

    /*
        FAKE METHODS!!!
     */

    fun createList():List<Coupon>{
        val list:MutableList<Coupon> = ArrayList()
        list.add(Coupon("empty_space",Coupon.HEADER_TYPE))
        list.add(Coupon("as2fcv",Coupon.COUPON_TYPE,"Vegetable ECO Biscuit","https://www.goodhousekeeping.com/health/diet-nutrition/g26975180/food-for-hair-growth/","3 days to finish",false
            ,"-20%","My Best Veggie","This is a product description","Limited to 156 units","Coupon redeemable 1 time","5707984"))
        list.add(Coupon("des3gt",Coupon.COUPON_TYPE,"Activia 0%","https://www.goodhousekeeping.com/health/diet-nutrition/g26975180/food-for-hair-growth/","3 days to finish",false
            ,"-20%","Danone","This is a product description","Limited to 2500 units","Coupon redeemable 1 time","5707984"))
        list.add(Coupon("sxert5",Coupon.COUPON_TYPE,"La Piara","https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201707/05/00118002400242____1__600x600.jpg","3 days to finish",false
            ,"-26%","La Piara","This is a product description","Limited to 4 units","Coupon redeemable 1 time","5707984"))
        list.add(Coupon("abrfed",Coupon.COUPON_TYPE,"MAXI hamburguer bread","https://www.goodhousekeeping.com/health/diet-nutrition/g26975180/food-for-hair-growth/","20 days to finish",false
            ,"-20%","MAXI","This is a product description","Limited to 23 units","Coupon redeemable 1 time","5707984"))
        list.add(Coupon("sdfsdf",Coupon.COUPON_TYPE,"Digestive chocolate","https://sc02.alicdn.com/kf/UTB8xPD8M5aMiuJk43PTq6ySmXXas/49039/UTB8xPD8M5aMiuJk43PTq6ySmXXas.jpg","1 days to finish",false
            ,"-20%","NO_BRAND","This is a product description","Limited to 20 units","Coupon redeemable 1 time","5707984"))
        return list
    }

}