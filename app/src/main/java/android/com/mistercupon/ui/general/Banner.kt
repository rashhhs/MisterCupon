package android.com.mistercupon.ui.general

import android.com.mistercupon.R
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.coordinatorlayout.widget.CoordinatorLayout
import kotlinx.android.synthetic.main.banner_layout.view.*

class Banner: CoordinatorLayout {

    private var _contentText: String? = ""
    private var _leftButtonText: String? = ""
    private var _rightButtonText: String? = ""

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int){
        View.inflate(context, R.layout.banner_layout,this)
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.Banner, defStyle, 0
        )

        contentText = typedArray.getString(
            R.styleable.Banner_contentText
        )

        leftButtonText = typedArray.getString(
            R.styleable.Banner_leftButtonText
        )

        rightButtonText = typedArray.getString(
            R.styleable.Banner_rightButtonText
        )
    }

    var contentText: String?
        get() = _contentText
        set(value) {
            _contentText = value
            contentTextView.text = value
        }

    var leftButtonText: String?
        get() = _leftButtonText
        set(value) {
            _leftButtonText = value
            leftButton.text = value
        }

    var rightButtonText: String?
        get() = _rightButtonText
        set(value) {
            _rightButtonText = value
            rightButton.text = value
        }

    fun dismiss(){
        this.collapse()
    }

    fun show(){
        this.expand()
    }

    fun setLeftButtonAction(action: () -> Unit) = leftButton.setOnClickListener { action() }
    fun setRightButtonAction(action: () -> Unit) = rightButton.setOnClickListener { action() }

    private fun View.expand() {
        val matchParentMeasureSpec = MeasureSpec.makeMeasureSpec((this.getParent() as View).width, MeasureSpec.EXACTLY)
        val wrapContentMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        this@expand.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
        this@expand.visibility = View.VISIBLE

        val initialHeight = this@expand.measuredHeight

        val animation = object: Animation(){

            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 1f) {
                    this@expand.visibility = View.VISIBLE
                } else {
                    this@expand.layoutParams.height = 0 + (initialHeight * interpolatedTime).toInt()
                    this@expand.requestLayout()
                }
            }
            override fun willChangeBounds(): Boolean = true
        }
        animation.duration = (initialHeight / this.resources.displayMetrics.density).toInt().toLong()
        this.startAnimation(animation)
    }

    private fun View.collapse() {
        val initialHeight = this.measuredHeight

        val animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 1f) {
                    this@collapse.visibility = View.GONE
                } else {
                    this@collapse.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                    this@collapse.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean = true
        }

        animation.duration = (initialHeight / this.context.resources.displayMetrics.density).toInt().toLong()
        this.startAnimation(animation)
    }
}