package com.ktmmoe.patient.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ktmmoe.patient.R
import kotlinx.android.synthetic.main.dialog_fragment_no_profile_info.*

/**
 * Created by ktmmoe on 29, November, 2020
 **/
class NoProfileInfoDialogFragment: DialogFragment() {

    override fun getTheme(): Int = R.style.RoundedCornersDialog

    companion object {
        private var confirmCallback: ()-> Unit = {}

        fun newInstance(confirmCallback: ()-> Unit = {}): NoProfileInfoDialogFragment {
            this.confirmCallback = confirmCallback
            return NoProfileInfoDialogFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_fragment_no_profile_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivCancelCross.setOnClickListener { dismiss() }

        fillIn.setOnClickListener {
            confirmCallback.invoke()
            dismiss()
        }
    }
}