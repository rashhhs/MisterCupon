package android.com.mistercupon.ui.detail

import android.com.mistercupon.BaseFragment
import android.com.mistercupon.MainActivity
import android.com.mistercupon.R
import android.com.mistercupon.repository.model.data.Coupon
import android.com.mistercupon.ui.list.data.CouponView
import android.com.mistercupon.ui.utils.ImageUtils
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProviders

class CouponDetailFragment:BaseFragment() {
    companion object {
        fun newInstance() = CouponDetailFragment()
    }

    private lateinit var viewModel:CouponDetailViewModel

    private lateinit var mDiscountText:TextView
    private lateinit var mBrandTextView: TextView
    private lateinit var mProductTextView: TextView
    private lateinit var mDescriptionTextView: TextView
    private lateinit var mTimeToExpireTextView:TextView
    private lateinit var mLimitedUnitTextView: TextView
    private lateinit var mProductCodeTextView:TextView
    private lateinit var toolbarImage:ImageView
    private lateinit var mBackButton: ImageView
    private lateinit var mConditionsContainer: ConstraintLayout
    private lateinit var activatedButton: ToggleButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.coupon_detail_layout, container, false)
        mDiscountText = view.findViewById(R.id.discount_text)
        mBrandTextView = view.findViewById(R.id.brand_text)
        mProductTextView = view.findViewById(R.id.product_text)
        mProductTextView = view.findViewById(R.id.product_text)
        mDescriptionTextView = view.findViewById(R.id.description_text)
        mTimeToExpireTextView = view.findViewById(R.id.timeToExpire_text)
        mLimitedUnitTextView = view.findViewById(R.id.limitation_title)
        mProductCodeTextView = view.findViewById(R.id.product_code)
        mBackButton = view.findViewById(R.id.back_button)
        toolbarImage = view.findViewById(R.id.toolbarImage)
        mConditionsContainer = view.findViewById(R.id.contiditions_container)
        activatedButton = view.findViewById(R.id.isActivated_button)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //TODO (R): Seems with the newest version ViewModelProviders is deprecated. Search the newest approach on the future
        viewModel = ViewModelProviders.of(this).get(CouponDetailViewModel::class.java)
        setContent()
    }

    fun setContent(){
        val couponView = arguments?.getSerializable("coupon")

        if(couponView is CouponView){
            mDiscountText.text = couponView.discount
            mBrandTextView.text = couponView.brand
            mProductTextView.text = couponView.title
            mDescriptionTextView.text = couponView.productDescription
            mTimeToExpireTextView.text = couponView.daysToExpire
            mLimitedUnitTextView.text = couponView.limitationUnits
            mProductCodeTextView.text = couponView.productCode
            if(couponView.img_background == null){
                ImageUtils.loadImage(couponView.imgUrl ?: "",R.drawable.placeholder_image,toolbarImage,context!!)
            }
            initActiveButton(couponView.title, couponView.isActivated)
        }

        initBackButton()
        initImageConditions()
    }

    fun initBackButton(){
        mBackButton.setOnClickListener{v: View? ->
            activity?.onBackPressed()
        }
    }

    fun initImageConditions(){
        mConditionsContainer.setOnClickListener{v: View? ->
            val context: Context? = activity
            if(context is MainActivity)
                context.addFragment(R.id.container,ConditionsFragment.newInstance())
        }
    }

    fun initActiveButton(title:String, isActive:Boolean){
        activatedButton.isChecked = isActive
        activatedButton.setOnCheckedChangeListener { buttonView, isChecked ->
            val coupon = Coupon()
            coupon.updateCouponActivation(title,isChecked)
        }
    }
}