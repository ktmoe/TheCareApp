package com.ktmmoe.doctor.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import com.ktmmoe.doctor.R
import com.ktmmoe.doctor.adapters.PrescriptMedicineAdapter
import com.ktmmoe.doctor.dialogs.PrescriptMedicineDialogFragment
import com.ktmmoe.doctor.mvp.presenters.PrescriptPresenter
import com.ktmmoe.doctor.mvp.presenters.impls.PrescriptPresenterImpl
import com.ktmmoe.doctor.mvp.views.PrescriptView
import com.ktmmoe.shared.activities.BaseActivity
import com.ktmmoe.shared.data.vos.Consultation
import com.ktmmoe.shared.data.vos.PrescriptMedicine
import kotlinx.android.synthetic.main.activity_prescript.*

class PrescriptActivity : BaseActivity(), PrescriptView {
    private lateinit var mPresenter: PrescriptPresenter
    private lateinit var mPrescriptMedicineAdapter: PrescriptMedicineAdapter

    companion object {
        fun intent(context: Context, consultationRecord: Consultation) : Intent {
            val intent = Intent(context, PrescriptActivity::class.java)
            return intent.putExtra("consultation-record", consultationRecord)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prescript)

        setupPresenter()
        setupRecycler()
        setupListeners()
        setupActionBar(toolbar, resources.getString(R.string.prescript))
        mPresenter.onUiReady(this, this)
    }

    private fun setupRecycler() {
        mPrescriptMedicineAdapter = PrescriptMedicineAdapter(mPresenter)
        rvMedicine.adapter = mPrescriptMedicineAdapter
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<PrescriptPresenterImpl, PrescriptView>()
    }

    private fun setupListeners() {
        etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mPresenter.onTapSearchMedicine(etSearch.text.toString())
                return@setOnEditorActionListener true
            }
            false
        }

        prescriptAndEndSession.setOnClickListener { mPresenter.onTapPrescriptAndEndSession() }
    }

    override fun showMedicineList(medicines: List<PrescriptMedicine>) {
        mPrescriptMedicineAdapter.setNewData(medicines)
    }

    override fun prescriptAndEndSession(medicines: List<PrescriptMedicine>) {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun showMedicinePrescriptBox(medicine: PrescriptMedicine, onAddMedicine: (PrescriptMedicine) -> Unit) {
        PrescriptMedicineDialogFragment.newInstance(medicine, onAddMedicine)
                .show(supportFragmentManager, PrescriptActivity::class.java.simpleName)
    }

    override fun getPassedExtra(): Consultation = intent.getSerializableExtra("consultation-record") as Consultation
}