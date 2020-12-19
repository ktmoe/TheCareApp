package com.ktmmoe.patient.viewholders

import android.view.View
import androidx.core.widget.doOnTextChanged
import com.ktmmoe.shared.data.vos.CaseSummary
import com.ktmmoe.shared.data.vos.SpecialQuestion
import com.ktmmoe.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_q_and_a.view.*
import java.util.*

/**
 * Created by ktmmoe on 10, December, 2020
 **/
class SpecialQAndAViewHolder(private val itemView: View, private val mDelegateSpecial: SpecialQAndAViewHolderDelegate): BaseViewHolder<SpecialQuestion>(itemView) {

    override fun bindData(data: SpecialQuestion) {
        itemView.question.text = data.name
        itemView.etAnswer.doOnTextChanged { text, _, _, _ ->
            mDelegateSpecial.onAnswered(CaseSummary(id = data.id, question = data.name, answer = text.toString()))
        }
    }
}

interface SpecialQAndAViewHolderDelegate {
    fun onAnswered(caseSummary: CaseSummary)
}