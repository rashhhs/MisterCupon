package android.com.mistercupon.ui.list

import android.com.mistercupon.BaseFragment
import android.com.mistercupon.MainActivity
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.com.mistercupon.R
import android.com.mistercupon.ui.list.data.CouponView
import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.coupon_list_fragment.*

class CouponListFragment : BaseFragment(),CouponViewContract {

    lateinit var mRecyclerView: RecyclerView
    lateinit var mTextView:TextView

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
        mTextView = view.findViewById(R.id.textView)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //TODO (R): Seems with the newest version ViewModelProviders is deprecated. Search the newest approach on the future
        viewModel = ViewModelProviders.of(this).get(CouponListViewModel::class.java)
        viewModel.setCountractView(this)
        initAdapter()
    }

    fun initAdapter(){
        val adapter = CouponListAdapter(activity)

        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = adapter

        viewModel.coupons.observe(viewLifecycleOwner, Observer { couponsList -> adapter.submitList(couponsList)})
    }

    override fun getBackgroundPlaceholder(): Drawable? {
        if(context == null) return null
        return ContextCompat.getDrawable(context!!,R.drawable.rounded_thumbnail)
    }

    override fun getPlaceholderTextColor(): Int {
        if(context == null)return 0
        return ContextCompat.getColor(context!!,R.color.thumbnail)
    }

    override fun getDefaultTextColor(): Int {
        if(context == null)return 0
        return mTextView.textColors.defaultColor
    }
}
