package com.ktmmoe.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.ktmmoe.doctor.R
import com.ktmmoe.doctor.mvp.presenters.ProfilePresenter
import com.ktmmoe.doctor.mvp.presenters.impls.ProfilePresenterImpl
import com.ktmmoe.doctor.mvp.views.ProfileView
import com.ktmmoe.shared.activities.BaseActivity
import com.ktmmoe.shared.data.vos.Doctor
import com.ktmmoe.shared.utils.load
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.item_new_consultation_request.*
import kotlinx.android.synthetic.main.layout_doctor_info.*

class ProfileActivity : BaseActivity(), ProfileView {
    private lateinit var mPresenter: ProfilePresenter

    companion object {
        fun intent(context: Context, doctor: Doctor) : Intent {
            return Intent(context, ProfileActivity::class.java).apply {
                putExtra("doctor", doctor)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setupPresenter()
        setupActionBar(toolbar)
        setupOnClickListeners()
        mPresenter.onUiReady(this, this)
    }

    private fun setupOnClickListeners () {
        tvChangePassword.setOnClickListener { mPresenter.onTapChangePassword() }
        tvLogout.setOnClickListener { mPresenter.onTapLogout() }
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<ProfilePresenterImpl, ProfileView>()
    }

    override fun getPassedDoctor(): Doctor = intent.getSerializableExtra("doctor") as Doctor

    override fun showDoctorProfile(doctor: Doctor) {
        this.ivDoctorProfile.load(doctor.image)
        this.doctorName.text = doctor.name
        this.doctorPhone.text = doctor.phone
        this.doctorSpeciality.text = doctor.specialtyName
        this.degrees.text = doctor.degrees
        this.doctorProfile.text = doctor.profile

        this.tvDob.text = doctor.dob
        this.tvExperience.text = doctor.experience
        this.tvGender.text = doctor.gender
        this.tvAddress.text = doctor.address
    }

    override fun navigateToEditProfile() {
        startActivity(EditProfileActivity.intent(this))
    }

    override fun navigateToLoginScreen() {
        finishAffinity()
        startActivity(LoginActivity.intent(this))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.doctor_profile_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.edit_profile) {
            mPresenter.onTapEdit()
            true
        } else false
    }

}