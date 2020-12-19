package com.ktmmoe.patient.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ktmmoe.patient.R
import com.ktmmoe.shared.utils.consultationConfirmImage
import com.ktmmoe.shared.utils.load
import kotlinx.android.synthetic.main.dialog_fragment_consultation_confirm.*

/**
 * Created by ktmmoe on 29, November, 2020
 **/
class ConsultationConfirmDialogFragment: DialogFragment() {

    override fun getTheme(): Int = R.style.RoundedCornersDialog

    companion object {
        private var confirmCallback: ()-> Unit = {}
        private var category: String = ""

        fun newInstance(confirmCallback: ()-> Unit = {}, category: String): ConsultationConfirmDialogFragment {
            this.confirmCallback = confirmCallback
            this.category = category
            return ConsultationConfirmDialogFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_fragment_consultation_confirm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        consultationConfirmMessage.text = String.format(getString(R.string.consultation_confirm_message), category)

        consultationCancel.setOnClickListener { dismiss() }

        consultationConfirm.setOnClickListener {
            confirmCallback.invoke()
            dismiss()
        }
    }
}