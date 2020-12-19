package com.ktmmoe.patient.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ktmmoe.patient.R
import com.ktmmoe.patient.activities.EditProfileActivity
import com.ktmmoe.patient.activities.LoginActivity
import com.ktmmoe.patient.dialogs.NoProfileInfoDialogFragment
import com.ktmmoe.patient.mvp.presenters.ProfilePresenter
import com.ktmmoe.patient.mvp.presenters.impls.ProfilePresenterImpl
import com.ktmmoe.patient.mvp.views.ProfileView
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.fragments.BaseFragment
import com.ktmmoe.shared.utils.load
import com.ktmmoe.shared.viewpods.PatientInfoViewPod
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : BaseFragment(), ProfileView {
    private lateinit var mPresenter: ProfilePresenter
    private lateinit var mPatientInfoViewPod: PatientInfoViewPod

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_profile, container, false)
        (activity as AppCompatActivity).setSupportActionBar(v.toolbar)
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPresenter()
        setupViewPod()
        setupOnClickListeners()
        mPresenter.onUiReady(requireContext(), this)
    }

    private fun setupOnClickListeners() {
        tvChangePassword.setOnClickListener { mPresenter.onTapChangePassword() }
        tvLogout.setOnClickListener { mPresenter.onTapLogout() }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.patient_profile_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.edit_profile) {
            mPresenter.onTapEditProfile()
            true
        } else false
    }

    private fun setupViewPod() {
        mPatientInfoViewPod = vpPatientInfo as PatientInfoViewPod
        mPatientInfoViewPod.onlyOneTimeInfo()
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<ProfilePresenterImpl, ProfileView>()
    }

    override fun showPatientInfo(patient: Patient) {
        mPatientInfoViewPod.setData(patient = patient)
        view?.let {
            it.ivPatient.load(patient.image)
            it.tvPatientName.text = patient.name
            it.tvPatientPhone.text = patient.phone
        }
    }

    override fun showNoPatientInfoDialog() {
        NoProfileInfoDialogFragment.newInstance {
            mPresenter.onTapEditProfile()
        }.show(requireFragmentManager(), ProfileFragment::class.java.simpleName)
    }

    override fun navigateToEditProfileScreen(patient: Patient) {
        startActivity(EditProfileActivity.intent(requireContext()))
    }

    override fun navigateToLoginScreen() {
        requireActivity().finishAffinity()
        startActivity(LoginActivity.intent(requireContext()))
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}