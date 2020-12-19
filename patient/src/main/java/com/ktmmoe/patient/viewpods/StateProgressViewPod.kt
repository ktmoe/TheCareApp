package com.ktmmoe.patient.viewpods

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.ktmmoe.patient.R
import kotlinx.android.synthetic.main.view_pod_state_progress.view.*

/**
 * Created by ktmmoe on 04, December, 2020
 **/
class StateProgressViewPod @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var isCompleteAllSteps = false

    private var mDelegate : Delegate? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        setUpListeners()
    }

    fun setDelegate(delegate: Delegate){
        mDelegate=delegate
    }

    fun completeAllSteps(){
        isCompleteAllSteps = true
    }

    fun setStepDescription(
            step1Desc: String,
            step2Desc: String
    ) {

        tvDesc1.text = step1Desc
        tvDesc2.text = step2Desc
    }

    private fun onTapStep1() {
        currentAtStep1()
    }

    private fun onTapStep2() {
        currentAtStep2()
    }

    private fun setUpListeners() {
        step1.setOnClickListener {
            onTapStep1()
            mDelegate?.onTapStep1()
        }

        step2.setOnClickListener {
            onTapStep2()
            mDelegate?.onTapStep2()
        }
    }

    fun currentAtStep1() {
        step1.setImageResource(R.drawable.ic_check_blue)
        step2.setImageResource(R.drawable.ic_check_2)
        view2.setBackgroundColor(ContextCompat.getColor(context, R.color.blue))
        view3.setBackgroundColor(ContextCompat.getColor(context, R.color.darker_gray))
    }

    fun currentAtStep2() {
        completeAllSteps()
        step2.setImageResource(R.drawable.ic_check_blue)
        view2.setBackgroundColor(ContextCompat.getColor(context, R.color.blue))
        view3.setBackgroundColor(ContextCompat.getColor(context, R.color.blue))
    }

    interface Delegate{
        fun onTapStep1()
        fun onTapStep2()
    }

}