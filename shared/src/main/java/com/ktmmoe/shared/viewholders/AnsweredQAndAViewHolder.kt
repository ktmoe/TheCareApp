package com.ktmmoe.shared.viewholders

import android.view.View
import com.ktmmoe.shared.data.vos.CaseSummary
import kotlinx.android.synthetic.main.item_answered_q_and_a.view.*

/**
 * Created by ktmmoe on 30, November, 2020
 **/
class AnsweredQAndAViewHolder(private val itemView: View): BaseViewHolder<CaseSummary>(itemView) {

    override fun bindData(data: CaseSummary) {
        itemView.question.text = data.question
        itemView.answer.text = data.answer
    }
}