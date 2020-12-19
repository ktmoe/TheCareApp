package com.ktmmoe.doctor.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ktmmoe.doctor.R
import com.ktmmoe.shared.utils.consultationConfirmImage
import com.ktmmoe.shared.utils.load
import kotlinx.android.synthetic.main.dialog_fragment_select_time.*

/**
 * Created by ktmmoe on 30, November, 2020
 **/
class SelectTimeDialogFragment: DialogFragment() {

    override fun getTheme(): Int = R.style.RoundedCornersDialog

    companion object {
        var dismissCallback: (Int, Int)->Unit = {_,_->}
        fun newInstance(cb: (Int, Int)-> Unit = {_,_->}): SelectTimeDialogFragment {
            dismissCallback = cb
            return SelectTimeDialogFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_fragment_select_time, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivSelectTime.load(consultationConfirmImage)

        btnSelectTime.setOnClickListener {
            dismiss()
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            dismissCallback.invoke(timePicker.hour, timePicker.minute)
        }
        }
    }
}