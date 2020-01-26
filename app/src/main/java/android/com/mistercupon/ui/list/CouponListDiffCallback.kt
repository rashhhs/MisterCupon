package android.com.mistercupon.ui.list

import android.com.mistercupon.ui.list.data.CouponView
import androidx.recyclerview.widget.DiffUtil

class CouponListDiffCallback: DiffUtil.ItemCallback<CouponView>() {
    override fun areItemsTheSame(oldItem: CouponView, newItem: CouponView): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CouponView, newItem: CouponView): Boolean {
        return oldItem.id == newItem.id
    }
}