package android.com.mistercupon.ui.detail

import android.com.mistercupon.BaseFragment
import android.com.mistercupon.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

class CouponDetailFragment:BaseFragment() {
    companion object {
        fun newInstance() = CouponDetailFragment()
    }

    private lateinit var viewModel:CouponDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.coupon_detail_layout, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //TODO (R): Seems with the newest version ViewModelProviders is deprecated. Search the newest approach on the future
        viewModel = ViewModelProviders.of(this).get(CouponDetailViewModel::class.java)
    }
}