package com.ktmmoe.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ktmmoe.doctor.R
import com.ktmmoe.doctor.mvp.presenters.LoginPresenter
import com.ktmmoe.doctor.mvp.presenters.impls.LoginPresenterImpl
import com.ktmmoe.doctor.mvp.views.LoginView
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
        facebookSignIn.setOnClickListener { mPresenter.onTapFacebookSignIn() }
        forgetPassword.setOnClickListener { mPresenter.onTapForgetPassword() }
    }

    override fun navigateToMain() {
        finish()
        startActivity(MainActivity.intent(this))
    }

    override fun navigateToRegister() {
        startActivity(RegisterActivity.intent(this))
    }
    
    companion object {
        fun intent(context: Context): Intent = Intent(context, LoginActivity::class.java)
    }
}