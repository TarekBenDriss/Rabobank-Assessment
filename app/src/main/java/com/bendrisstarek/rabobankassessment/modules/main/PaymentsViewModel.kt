package com.bendrisstarek.rabobankassessment.modules.main

import android.app.Application
import com.bendrisstarek.rabobankassessment.App
import com.bendrisstarek.rabobankassessment.base.BaseViewModel
import com.bendrisstarek.rabobankassessment.datasource.local.entity.Payment
import com.bendrisstarek.rabobankassessment.datasource.repository.PaymentsRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * this class represents the payment's viewmodel
 */
class PaymentsViewModel(application: Application) : BaseViewModel(application) {
    @JvmField
    @Inject
    var mRepository: PaymentsRepository? = null


    /**
     * this function calls the repository method to get payments
     */
    fun getUserPayments(userId: String?): Single<List<Payment>?>? {
        return mRepository!!.getUserPayments(userId) //
                ?.subscribeOn(Schedulers.computation()) //
                ?.observeOn(AndroidSchedulers.mainThread())
    }

    // constructor
    init {
        App.getDataComponent().inject(this)
    }
}