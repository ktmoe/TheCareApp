package com.ktmmoe.doctor.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ktmmoe.doctor.R
import com.ktmmoe.doctor.adapters.EasyQuestionAdapter
import com.ktmmoe.doctor.mvp.presenters.EasyQuestionsPresenter
import com.ktmmoe.doctor.mvp.presenters.impls.EasyQuestionsPresenterImpl
import com.ktmmoe.doctor.mvp.views.EasyQuestionsView
import com.ktmmoe.shared.activities.BaseActivity
import com.ktmmoe.shared.data.vos.SpecialQuestion
import kotlinx.android.synthetic.main.activity_easy_questions.*

class EasyQuestionsActivity : BaseActivity(), EasyQuestionsView {

    private lateinit var mPresenter: EasyQuestionsPresenter
    private lateinit var mEasyQuestionAdapter: EasyQuestionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_questions)

        setupPresenter()
        setupActionBar(toolbar = toolbar, resources.getString(R.string.questions))
        setupRecyclerViews()
        mPresenter.onUiReady(this, this)
    }

    private fun setupRecyclerViews() {
        mEasyQuestionAdapter = EasyQuestionAdapter(mPresenter)
        rvEasyQuestions.adapter = mEasyQuestionAdapter
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<EasyQuestionsPresenterImpl, EasyQuestionsView>()
    }

    override fun showPredefinedQuestions(questions: List<String>) {
        mEasyQuestionAdapter.setNewData(questions)
    }

    override fun finishAndSetResult(easyQuestion: String?) {
        val returnIntent = Intent()
        returnIntent.putExtra("question", easyQuestion)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    companion object {
        fun intent(context: Context): Intent = Intent(context, EasyQuestionsActivity::class.java)
    }
}