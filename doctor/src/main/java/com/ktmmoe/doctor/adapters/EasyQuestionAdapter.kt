package com.ktmmoe.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ktmmoe.doctor.R
import com.ktmmoe.doctor.viewholders.EasyQuestionViewHolder
import com.ktmmoe.doctor.viewholders.EasyQuestionViewHolderDelegate
import com.ktmmoe.shared.adapters.BaseRecyclerAdapter

/**
 * Created by ktmmoe on 03, December, 2020
 **/
class EasyQuestionAdapter(private val mDelegate: EasyQuestionViewHolderDelegate) : BaseRecyclerAdapter<EasyQuestionViewHolder, String>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EasyQuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_special_question, parent, false)
        return EasyQuestionViewHolder(view, mDelegate)
    }
}