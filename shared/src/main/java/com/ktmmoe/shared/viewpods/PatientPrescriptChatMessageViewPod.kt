package com.ktmmoe.shared.viewpods

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.ktmmoe.shared.data.vos.PrescriptMedicine
import com.ktmmoe.shared.utils.load
import kotlinx.android.synthetic.main.patient_prescript_chat_message.view.*

/**
 * Created by ktmmoe on 15, December, 2020
 **/
class PatientPrescriptChatMessageViewPod @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var mDelegate: PatientPrescriptChatMessageViewPodDelegate? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        btnCheckout.setOnClickListener {
            mDelegate?.onTapCheckout()
        }
    }

    fun setData(medicines: List<PrescriptMedicine>, image: String, date: String) {
        var medicineList = ""
        medicines.forEach { medicineList += "${it.medicine.name}\n" }
        tvMedicines.text = medicineList
        ivSender.load(image)
        tvSentTime.text = date
    }

    fun setDelegate(delegate: PatientPrescriptChatMessageViewPodDelegate) {
        mDelegate = delegate
    }
}

interface PatientPrescriptChatMessageViewPodDelegate {
    fun onTapCheckout()
}