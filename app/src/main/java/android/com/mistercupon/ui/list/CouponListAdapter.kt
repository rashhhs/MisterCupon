package android.com.mistercupon.ui.list

import android.com.mistercupon.MainActivity
import android.com.mistercupon.R
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.coupon_list_row, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val unit = getItem(position)
        holder.title.text = unit?.title
        holder.title.background = unit?.title_background
        holder.title.setTextColor(unit?.title_color ?: 0)
        holder.timeToExpire.text = unit?.daysToExpire
        holder.timeToExpire.background = unit?.daysToExpire_background
        holder.timeToExpire.setTextColor(unit?.daysToExpire_color ?: 0)
    }

    inner class ViewHolder(item: View): RecyclerView.ViewHolder(item),View.OnClickListener{
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
}