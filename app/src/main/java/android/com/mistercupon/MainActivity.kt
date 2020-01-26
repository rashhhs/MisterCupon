package android.com.mistercupon

import android.com.mistercupon.repository.Repository
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.com.mistercupon.ui.list.CouponListFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

open class MainActivity:BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = Repository.instance
        repository.init(this)

        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            replaceFragment(R.id.container,CouponListFragment.newInstance())
        }
    }

}
