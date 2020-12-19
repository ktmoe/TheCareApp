package com.ktmmoe.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ktmmoe.doctor.R
import com.ktmmoe.doctor.mvp.presenters.RegisterPresenter
import com.ktmmoe.doctor.mvp.presenters.impls.RegisterPresenterImpl
import com.ktmmoe.doctor.mvp.views.RegisterView
import com.ktmmoe.shared.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), RegisterView {
    private lateinit var mPresenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setupPresenter()
        setupOnClicks()
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<RegisterPresenterImpl, RegisterView>()
    }

    private fun setupOnClicks() {
        btnRegister.setOnClickListener { mPresenter.onTapRegister(etMail.text.toString(), etPassword.text.toString(), etName.text.toString()) }
        back.setOnClickListener { mPresenter.onTapBack() }
        haveAccount.setOnClickListener { mPresenter.onTapLogin() }
    }

    override fun navigateToCompleteInfo() {
        finishAffinity()
        startActivity(EditProfileActivity.intent(this, true))
    }

    companion object {
        fun intent(context: Context) : Intent = Intent(context, RegisterActivity::class.java)
    }
}