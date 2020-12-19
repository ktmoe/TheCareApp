package com.ktmmoe.doctor.activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import com.ktmmoe.doctor.R
import com.ktmmoe.doctor.mvp.presenters.EditProfilePresenter
import com.ktmmoe.doctor.mvp.presenters.impls.EditProfilePresenterImpl
import com.ktmmoe.doctor.mvp.views.EditProfileView
import com.ktmmoe.shared.activities.BaseActivity
import com.ktmmoe.shared.data.vos.Doctor
import com.ktmmoe.shared.data.vos.Speciality
import com.ktmmoe.shared.utils.load
import kotlinx.android.synthetic.main.activity_edit_profile.*
import java.io.IOException
import java.util.*

class EditProfileActivity : BaseActivity(), EditProfileView {
    private lateinit var mPresenter: EditProfilePresenter
    private lateinit var mSpinnerArrayAdapter: ArrayAdapter<String>
    private var mSpecialtyList = listOf<Speciality>()

    companion object {
        private const val PICK_IMAGE_REQUEST = 199
        private var must : Boolean = false
        fun intent(context: Context, must: Boolean= false): Intent = Intent(context, EditProfileActivity::class.java)
                .apply { putExtra("must", must) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        must = intent.getBooleanExtra("must", false)

        setupPresenter()
        setupActionBar(toolbar, callback = {this.onBackPressed()})
        setupOnClickListeners()
        mPresenter.onUiReady(this, this)
    }

    private fun setupOnClickListeners() {
        ivDoctor.setOnClickListener { mPresenter.onTapProfilePhoto() }
        btnSave.setOnClickListener {
            val gender = if (maleRadio.isSelected) maleRadio.text else femaleRadio.text
            mPresenter.onTapSave(spinnerSpecialty.selectedItem.toString(), etName.text.toString(), etPhone.text.toString(), etExperience.text.toString(), gender.toString(), etAddress.text.toString(), etDegrees.text.toString(), etProfile.text.toString()) }

        dateOfDob.setOnClickListener { mPresenter.onTapDOB() }
        monthOfDob.setOnClickListener { mPresenter.onTapDOB() }
        yearOfDob.setOnClickListener { mPresenter.onTapDOB() }
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<EditProfilePresenterImpl, EditProfileView>()
    }

    override fun showOldDoctorInfo(doctor: Doctor) {
        this.ivDoctor.load(doctor.image)
        this.etName.setText(doctor.name)
        this.etPhone.setText(doctor.phone)

        val index = mSpecialtyList.map { it.id }.indexOf(doctor.specialtyId)
        spinnerSpecialty.setSelection(index)

        if (doctor.dob.isNotEmpty()) {
            val split = doctor.dob.split("-")
            showPickedDOB(split.first().toInt(), split[1].toInt(), split.last().toInt())
        }

        this.etExperience.setText(doctor.experience)
        val selectedGender = if (doctor.gender == getString(R.string.female)) R.id.femaleRadio else R.id.maleRadio
        this.genderGroup.check(selectedGender)

        this.etAddress.setText(doctor.address)
        this.etDegrees.setText(doctor.degrees)
        this.etProfile.setText(doctor.profile)
    }

    override fun showSpecialtySpinner(array: Array<Speciality>) {
        mSpecialtyList = array.toList()
        mSpinnerArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array.map { it.name })
        mSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSpecialty.adapter = mSpinnerArrayAdapter
    }

    override fun showSelectedPhoto(bitmap: Bitmap) {
        this.ivDoctor.load(bitmap)
    }

    override fun navigateBack() {
        if (must) {
            finishAffinity()
            startActivity(MainActivity.intent(this))
        } else finish()
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
        dateOfDob.text = day.toString()
        monthOfDob.text = month.toString()
        yearOfDob.text = year.toString()
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

    override fun onBackPressed() {
        if (must) showSnackBar("Please complete profile first.")
        else super.onBackPressed()
    }
}