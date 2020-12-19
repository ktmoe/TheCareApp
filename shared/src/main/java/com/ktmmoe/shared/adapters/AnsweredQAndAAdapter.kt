package com.ktmmoe.shared.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ktmmoe.shared.R
import com.ktmmoe.shared.data.vos.CaseSummary
import com.ktmmoe.shared.viewholders.AnsweredQAndAViewHolder

/**
 * Created by ktmmoe on 30, November, 2020
 **/
class AnsweredQAndAAdapter: BaseRecyclerAdapter<AnsweredQAndAViewHolder, CaseSummary>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnsweredQAndAViewHolder=
        AnsweredQAndAViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_answered_q_and_a, parent, false))

}