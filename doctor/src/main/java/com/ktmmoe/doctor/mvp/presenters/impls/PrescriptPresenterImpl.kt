package com.ktmmoe.doctor.mvp.presenters.impls

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.ktmmoe.doctor.mvp.presenters.PrescriptPresenter
import com.ktmmoe.doctor.mvp.views.PrescriptView
import com.ktmmoe.shared.data.models.DoctorModel
import com.ktmmoe.shared.data.models.impls.DoctorModelImpl
import com.ktmmoe.shared.data.vos.*
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ktmmoe on 02, December, 2020
 **/
class PrescriptPresenterImpl: PrescriptPresenter, AbstractBasePresenter<PrescriptView>() {
    private val selectedMedicines: MutableList<PrescriptMedicine> = mutableListOf()
    private var allMedicines: MutableList<PrescriptMedicine> = mutableListOf()

    private var mConsultation: Consultation  = Consultation()

    private val mDoctorModel: DoctorModel = DoctorModelImpl

    override fun onTapPrescriptAndEndSession() {
        if (selectedMedicines.isEmpty()) mView.showSnackBar("No Medicines Selected.")
        else mDoctorModel.givePrescriptionsAndEndConversation(mView.getPassedExtra(), selectedMedicines, {
            updateConsultedDate()
        }, {
            mView.showSnackBar(it)
        })
    }

    @SuppressLint("SimpleDateFormat")
    private fun updateConsultedDate() {
        val mDoctor = mDoctorModel.getPersistedDoctor().first()
        val consultation = mConsultation.copy(
                consultationStarted = true,
                doctor = mDoctor,
                doctorId = mDoctor.id,
                patientId = mConsultation.patient.id,
                consultedDate = SimpleDateFormat("dd-mm-yy").format(Date())
        )
        mDoctorModel.updateConsultationAfterEnded(consultation, {
            mView.prescriptAndEndSession(selectedMedicines)
        }, {
            mView.showSnackBar(it)
        })
    }

    override fun onTapSearchMedicine(input: String) {
        val filtered = allMedicines.filter { it.medicine.name.toLowerCase(Locale.US).contains(input.toLowerCase(Locale.US)) }
        mView.showMedicineList(filtered)
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mConsultation = mView.getPassedExtra()
        Log.d("MEDICINES", "SpecialtyId ${mView.getPassedExtra()}")

        mDoctorModel.getMedicinesBySpecialtyId(mView.getPassedExtra().specialtyId, {
            Log.d("MEDICINES", "$it")
            allMedicines = it.map { PrescriptMedicine(medicine = it) }.toMutableList()
            mView.showMedicineList(it.map { PrescriptMedicine(medicine = it) })
        }, {
            mView.showSnackBar(it)
        })
    }

    override fun onTapPrescriptMedicine(medicine: PrescriptMedicine) {
        mView.showMedicinePrescriptBox(medicine){
            if (selectedMedicines.contains(PrescriptMedicine(it.medicine))) selectedMedicines.remove(PrescriptMedicine(it.medicine))
            if (it.routine.tab >= 1) selectedMedicines.add(it)
            val index = allMedicines.indexOf(PrescriptMedicine(it.medicine))
            allMedicines[index] = it
            mView.showMedicineList(allMedicines)
        }
    }
}