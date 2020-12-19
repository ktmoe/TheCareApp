package com.ktmmoe.patient.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ktmmoe.patient.R
import com.ktmmoe.patient.mvp.presenters.LoginPresenter
import com.ktmmoe.patient.mvp.presenters.impls.LoginPresenterImpl
import com.ktmmoe.patient.mvp.views.LoginView
import com.ktmmoe.shared.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginView {
    private lateinit var mPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupPresenter()
        setupOnClicks()
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<LoginPresenterImpl, LoginView>()
    }

    private fun setupOnClicks() {
        btnLogin.setOnClickListener { mPresenter.onTapLogin(etMail.text.toString(), etPassword.text.toString()) }
        btnRegister.setOnClickListener { mPresenter.onTapRegister() }
        forgetPassword.setOnClickListener { mPresenter.onTapForgetPassword() }
        facebookSignIn.setOnClickListener { mPresenter.onTapFacebookSignIn() }
    }

    override fun navigateToMain() {
        finish()
        startActivity(MainActivity.intent(this))
    }

    override fun navigateToRegister() {
        startActivity(RegisterActivity.intent(this))
    }

    companion object {
        fun intent(context: Context) : Intent = Intent(context, LoginActivity::class.java)
    }
}