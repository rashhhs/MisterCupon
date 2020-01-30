package android.com.mistercupon.ui.list

import android.com.mistercupon.BaseFragment
import android.com.mistercupon.R
import android.com.mistercupon.ui.general.Banner
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.coupon_list_fragment.*


class CouponListFragment : BaseFragment(),CouponViewContract {

    lateinit var mRecyclerView: RecyclerView
    lateinit var mTextView:TextView
    lateinit var mAppBarLayout: AppBarLayout
    lateinit var mCollapsingToolbar:CollapsingToolbarLayout
    lateinit var mBanner: Banner

    var couponsActive:String = "0 coupons active"

    companion object {
        fun newInstance() = CouponListFragment()
    }

    private lateinit var viewModel: CouponListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.coupon_list_fragment, container, false)
        mRecyclerView = view.findViewById(R.id.recyclerview)
        mTextView = view.findViewById(R.id.couponsActiveTextView)
        mAppBarLayout = view.findViewById(R.id.appBar)
        mCollapsingToolbar = view.findViewById(R.id.collapsingToolbar)
        mBanner = view.findViewById(R.id.banner)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //TODO (R): Seems with the newest version ViewModelProviders is deprecated. Search the newest approach on the future
        viewModel = ViewModelProviders.of(this).get(CouponListViewModel::class.java)
        viewModel.start()
        viewModel.setCountractView(this)
        initAdapter()
        initAppBarLayout()
        initBanner()
    }

    fun initAdapter(){
        val adapter = CouponListAdapter(activity)

        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = adapter

        viewModel.coupons.observe(viewLifecycleOwner, Observer { couponsList ->
            adapter.submitList(couponsList)
        })
        viewModel.couponsActive.observe(viewLifecycleOwner, Observer { t ->
           updateCouponsActive(t)
        })
    }

    fun initAppBarLayout(){
        mAppBarLayout.addOnOffsetChangedListener(object : OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    mCollapsingToolbar.setTitle(couponsActive)
                    mTextView.visibility = View.GONE
                    isShow = false
                } else if (!isShow) {
                    mCollapsingToolbar.setTitle(getString(R.string.title_list))
                    mTextView.visibility = View.VISIBLE
                    isShow = true
                }
            }
        })
    }

    fun initBanner(){
        hideBanner()
        mBanner.setLeftButtonAction{mBanner.dismiss()}
        mBanner.setRightButtonAction { mBanner.dismiss() }
    }

    fun updateCouponsActive(num_actives:Int){
        val activeCoupons = "$num_actives coupons activated"
        mTextView.text = activeCoupons
        couponsActive = activeCoupons
    }

    fun showBanner(){
        mBanner.show()
    }

    fun hideBanner(){
        mBanner.dismiss()
    }

    override fun getBackgroundPlaceholder(): Drawable? {
        if(context == null) return null
        return ContextCompat.getDrawable(context!!, R.drawable.rounded_thumbnail)
    }

    override fun getPlaceholderTextColor(): Int {
        if(context == null)return 0
        return ContextCompat.getColor(context!!,R.color.thumbnail)
    }

    override fun getDefaultTextColor(): Int {
        if(context == null)return 0
        return mTextView.textColors.defaultColor
    }

    override fun getViewVisible(): Int {
        return View.VISIBLE
    }

    override fun getViewGone(): Int {
        return View.GONE
    }
}
