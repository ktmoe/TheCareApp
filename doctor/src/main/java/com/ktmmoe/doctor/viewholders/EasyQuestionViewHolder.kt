package com.ktmmoe.doctor.viewholders

import android.view.View
import com.ktmmoe.shared.data.vos.SpecialQuestion
import com.ktmmoe.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_special_question.view.*

/**
 * Created by ktmmoe on 03, December, 2020
 **/

class EasyQuestionViewHolder(private val itemView: View, private val mDelegate: EasyQuestionViewHolderDelegate) : BaseViewHolder<String>(itemView){
    override fun bindData(data: String) {
        itemView.specialQuestion.text = data

        itemView.setOnClickListener { mDelegate.onTapEasyQuestion(data) }
    }
}

interface EasyQuestionViewHolderDelegate {
    fun onTapEasyQuestion(easyQuestion: String)
}