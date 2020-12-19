package com.ktmmoe.patient.activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import com.ktmmoe.patient.R
import com.ktmmoe.patient.mvp.presenters.EditProfilePresenter
import com.ktmmoe.patient.mvp.presenters.impls.EditProfilePresenterImpl
import com.ktmmoe.patient.mvp.views.EditProfileView
import com.ktmmoe.shared.activities.BaseActivity
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.utils.load
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_edit_profile.dateOfDob
import kotlinx.android.synthetic.main.activity_edit_profile.etAllergicPrescriptions
import kotlinx.android.synthetic.main.activity_edit_profile.etHeight
import kotlinx.android.synthetic.main.activity_edit_profile.monthOfDob
import kotlinx.android.synthetic.main.activity_edit_profile.spinnerBloodType
import kotlinx.android.synthetic.main.activity_edit_profile.yearOfDob
import kotlinx.android.synthetic.main.fragment_complete_one_time_questions.*
import java.io.IOException
import java.util.*

class EditProfileActivity : BaseActivity(), EditProfileView {
    private lateinit var mPresenter: EditProfilePresenter
    private var bloodTypes = arrayOf<String>()
    private lateinit var mSpinnerArrayAdapter: ArrayAdapter<String>

    companion object {
        private const val PICK_IMAGE_REQUEST = 199
        fun intent(context: Context): Intent = Intent(context, EditProfileActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        bloodTypes = resources.getStringArray(R.array.blood_types)

        setupPresenter()
        setupOnClickListeners()
        setupActionBar(toolbar)
        setupSpinner()
        mPresenter.onUiReady(this, this)
    }

    private fun setupSpinner() {
        mSpinnerArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.blood_types))
        mSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerBloodType.adapter = mSpinnerArrayAdapter
    }

    private fun setupOnClickListeners() {
        dateOfDob.setOnClickListener { mPresenter.onTapDOB() }
        monthOfDob.setOnClickListener { mPresenter.onTapDOB() }
        yearOfDob.setOnClickListener { mPresenter.onTapDOB() }
        ivPatient.setOnClickListener { mPresenter.onTapProfilePhoto() }
        btnSave.setOnClickListener {
            mPresenter.onTapSave(etName.text.toString(), etPhone.text.toString(), spinnerBloodType.selectedItem.toString(), "${etHeight.text} ft", etAllergicPrescriptions.text.toString(), etAddress.text.toString())
        }
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<EditProfilePresenterImpl, EditProfileView>()
    }

    override fun bindOldProfileInfo(patient: Patient) {
        this.ivPatient.load(patient.image)
        this.etName.setText(patient.name)
        this.etPhone.setText(patient.phone)
        this.etAddress.setText(patient.address)
        if (patient.dob.isNotEmpty()) {
            val split = patient.dob.split("-")
            this.dateOfDob.text = split.first()
            this.monthOfDob.text = split[1]
            this.yearOfDob.text = split.last()
        }
        if (patient.bloodType.isNotEmpty())
            this.spinnerBloodType.setSelection(bloodTypes.indexOf(patient.bloodType))

        this.etHeight.setText(patient.height.replace("ft", ""))
        this.etAllergicPrescriptions.setText(patient.allergicPrescriptions)
    }

    override fun showSelectedPhoto(bitmap: Bitmap) {
        this.ivPatient.load(bitmap)
    }

    override fun navigateBackToProfileScreen() {
        finish()
    }

    override fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun showDatePickerDialog(onPicked: (Int, Int, Int) -> Unit) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, R.style.MyDatePickerDialogTheme, { _, y, m, d ->
            onPicked(d, m+1, y)
        }, year, month, day).show()
    }

    override fun showPickedDOB(day: Int, month: Int, year: Int) {
        dateOfDob.text = "$day"
        monthOfDob.text = "$month"
        yearOfDob.text = "$year"
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }

            val filePath = data.data
            try {

                filePath?.let {
                    if (Build.VERSION.SDK_INT >= 29) {
                        val source: ImageDecoder.Source =
                            ImageDecoder.createSource(this.contentResolver, filePath)

                        val bitmap = ImageDecoder.decodeBitmap(source)
                        mPresenter.onPhotoTaken(bitmap)
                    } else {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            applicationContext.contentResolver, filePath
                        )
                        mPresenter.onPhotoTaken(bitmap)
                    }
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}