package com.ktmmoe.patient.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ktmmoe.patient.R
import com.ktmmoe.patient.mvp.presenters.RegisterPresenter
import com.ktmmoe.patient.mvp.presenters.impls.RegisterPresenterImpl
import com.ktmmoe.patient.mvp.views.RegisterView
import com.ktmmoe.shared.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : RegisterView, BaseActivity() {
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
        back.setOnClickListener { mPresenter.onTapBack() }
        btnRegister.setOnClickListener { mPresenter.onTapRegister(etMail.text.toString(), etPassword.text.toString(), etName.text.toString()) }
        haveAccount.setOnClickListener { mPresenter.onTapLogin() }
    }

    override fun navigateToMain() {
        finishAffinity()
        startActivity(MainActivity.intent(this))
    }

    companion object {
        fun intent(context: Context) : Intent = Intent(context, RegisterActivity::class.java)
    }
}