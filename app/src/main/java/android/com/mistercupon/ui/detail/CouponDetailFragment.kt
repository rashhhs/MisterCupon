package android.com.mistercupon.ui.detail

import android.com.mistercupon.BaseFragment
import android.com.mistercupon.MainActivity
import android.com.mistercupon.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import java.nio.InvalidMarkException

class CouponDetailFragment:BaseFragment() {
    companion object {
        fun newInstance() = CouponDetailFragment()
    }

    private lateinit var viewModel:CouponDetailViewModel

    private lateinit var mBackButton: ImageView
    private lateinit var mConditionsImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.coupon_detail_layout, container, false)
        mBackButton = view.findViewById(R.id.back_button)
        mConditionsImageView = view.findViewById(R.id.image_conditions)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //TODO (R): Seems with the newest version ViewModelProviders is deprecated. Search the newest approach on the future
        viewModel = ViewModelProviders.of(this).get(CouponDetailViewModel::class.java)
        initBackButton()
        initImageConditions()
    }

    fun initBackButton(){
        mBackButton.setOnClickListener{v: View? ->
            activity?.onBackPressed()
        }
    }

    fun initImageConditions(){
        mConditionsImageView.setOnClickListener{v: View? ->
            val context: Context? = activity
            if(context is MainActivity)
                context.addFragment(R.id.container,ConditionsFragment.newInstance())
        }
    }
}