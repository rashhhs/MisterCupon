package android.com.mistercupon

import android.annotation.SuppressLint
import android.com.mistercupon.repository.Repository
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity()  {

    open fun addFragment(layout:Int,fragment: BaseFragment){
        enableActionBar(true)
        supportFragmentManager.inTransaction { setCustomAnimations(R.animator.slide_in,
            R.animator.slide_out,
            R.animator.slide_in,
            R.animator.slide_out).add(layout, fragment) }
    }

    open fun replaceFragment(layout:Int,fragment: BaseFragment){
        enableActionBar(false)
        supportFragmentManager.inTransaction { replace(layout, fragment) }
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().addToBackStack(null).commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        if(supportFragmentManager.backStackEntryCount <= 1){
           enableActionBar(false)
        }
        return true
    }

    fun enableActionBar(isEnabled:Boolean){
        supportActionBar?.setHomeButtonEnabled(isEnabled)
        supportActionBar?.setDisplayHomeAsUpEnabled(isEnabled)
    }

    override fun onPause() {
        Repository.databaseCompositeDisposable.dispose()
        super.onPause()
    }

}