package android.com.mistercupon.ui.detail

import android.com.mistercupon.BaseFragment
import android.com.mistercupon.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class ConditionsFragment: BaseFragment() {
    lateinit var mBackButton:ImageView

    companion object {
        fun newInstance() = ConditionsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.conditions_layout_fragment, container, false)
        mBackButton = view.findViewById(R.id.back_button)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initBackButton()
    }

    fun initBackButton(){
        mBackButton.setOnClickListener{activity?.onBackPressed()}
    }
}