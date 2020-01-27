package android.com.mistercupon.ui.list

import android.com.mistercupon.MainActivity
import android.com.mistercupon.R
import android.com.mistercupon.repository.model.data.Coupon
import android.com.mistercupon.ui.detail.CouponDetailFragment
import android.com.mistercupon.ui.list.data.CouponView
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView

class CouponListAdapter(val context: Context?): PagedListAdapter<CouponView,CouponListAdapter.ViewHolder>(CouponListDiffCallback()) {
    val HEADER_TYPE = R.layout.header_list_row
    val COUPON_TYPE = R.layout.coupon_list_row

    override fun getItemViewType(position: Int): Int {
        val row = getItem(position)
        val type:Int
        when(row?.type) {
            Coupon.HEADER_TYPE -> type = HEADER_TYPE
            else -> {type = COUPON_TYPE}
        }
        return type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent,false)
        when(viewType){
            HEADER_TYPE -> return HeaderViewHolder(view)
            else -> return CouponViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val unit = getItem(position)
        if(holder is CouponViewHolder){
            holder.title.text = unit?.title
            holder.title.background = unit?.title_background
            holder.title.setTextColor(unit?.title_color ?: 0)
            holder.timeToExpire.text = unit?.daysToExpire
            holder.timeToExpire.background = unit?.daysToExpire_background
            holder.timeToExpire.setTextColor(unit?.daysToExpire_color ?: 0)
        }
    }

    open inner class ViewHolder(item: View): RecyclerView.ViewHolder(item)

    inner class CouponViewHolder(item:View):ViewHolder(item),View.OnClickListener{
        internal var image:ImageView = item.findViewById(R.id.img_coupon)
        internal var title: TextView = item.findViewById(R.id.title_coupon)
        internal var timeToExpire: TextView = item.findViewById(R.id.time_toExpire_coupon)
        internal var activatedButton: ToggleButton = item.findViewById(R.id.isActivated_button)

        init {
            item.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if(context is MainActivity)
                context.addFragment(R.id.container,CouponDetailFragment.newInstance())
        }
    }

    inner class HeaderViewHolder(item:View):ViewHolder(item)
}