package com.ktmmoe.doctor.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ktmmoe.doctor.mvp.presenters.EasyQuestionsPresenter
import com.ktmmoe.doctor.mvp.views.EasyQuestionsView
import com.ktmmoe.shared.data.models.impls.DoctorModelImpl
import com.ktmmoe.shared.data.vos.SpecialQuestion
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by ktmmoe on 03, December, 2020
 **/
class EasyQuestionsPresenterImpl: EasyQuestionsPresenter, AbstractBasePresenter<EasyQuestionsView>() {
    private val mDoctorModel = DoctorModelImpl
    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        val mDoctor = mDoctorModel.getPersistedDoctor().first()
        mDoctorModel.getEasyQuestionsBySpecialtyId(mDoctor.specialtyId, {
            mView.showPredefinedQuestions(it)
        }, {
            mView.showSnackBar(it)
        })
    }

    override fun onTapEasyQuestion(easyQuestion: String) {
        mView.finishAndSetResult(easyQuestion)
    }
}