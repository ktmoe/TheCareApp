package com.ktmmoe.patient.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ktmmoe.patient.R
import com.ktmmoe.patient.fragments.*
import com.ktmmoe.patient.mvp.presenters.CompletePatientInfoPresenter
import com.ktmmoe.patient.mvp.presenters.impls.CompletePatientInfoPresenterImpl
import com.ktmmoe.patient.mvp.views.CompletePatientInfoView
import com.ktmmoe.patient.viewpods.StateProgressViewPod
import com.ktmmoe.shared.activities.BaseActivity
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.data.vos.Speciality
import kotlinx.android.synthetic.main.activity_complete_patient_info.*

class CompletePatientInfoActivity : BaseActivity(), CompletePatientInfoView {
    private lateinit var mPresenter: CompletePatientInfoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_patient_info)

        setupActionBar(toolbar)
        setupPresenter()
        setupViewPod()
        mPresenter.onUiReady(this, this)
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<CompletePatientInfoPresenterImpl, CompletePatientInfoView>()
    }

    private fun setupViewPod() {
        mViewPodStateProgress = vpStateProgress as StateProgressViewPod
        mViewPodStateProgress.setStepDescription(resources.getString(R.string.general_questions), resources.getString(R.string.special_questions))
        mViewPodStateProgress.setDelegate(mPresenter)
    }

    companion object {
        private lateinit var mViewPodStateProgress: StateProgressViewPod
        fun intent(context: Context, patient: Patient, speciality: Speciality): Intent = Intent(context, CompletePatientInfoActivity::class.java)
                .apply {
                    putExtra("patient-info", patient)
                    putExtra("speciality", speciality)
                }

        fun currentAtStep1(){
            mViewPodStateProgress.currentAtStep1()
        }

        fun currentAtStep2() {
            mViewPodStateProgress.currentAtStep2()
        }
    }

    override fun getPassedPatient(): Patient = intent.getSerializableExtra("patient-info") as Patient

    override fun getPassedSpeciality(): Speciality = intent.getSerializableExtra("speciality") as Speciality

    override fun showCompleteGeneralQuestionsFragment(patient: Patient, speciality: Speciality) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.containerCompletePatientInfo, CompleteGeneralQuestionsFragment.newInstance(patient, speciality))
                .commit()
    }

    override fun showCompleteOneTimeQuestionsFragment(patient: Patient, speciality: Speciality) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.containerCompletePatientInfo, CompleteOneTimeQuestionsFragment.newInstance(patient, speciality))
                .commit()
    }

    override fun showSpecialQuestionsFragment(patient: Patient, speciality: Speciality) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.containerCompletePatientInfo, CompleteSpecialQuestionsFragment.newInstance(patient, speciality))
                .commit()
    }
}