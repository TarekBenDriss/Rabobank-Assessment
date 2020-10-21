package com.bendrisstarek.rabobankassessment.datasource.repository


import com.bendrisstarek.rabobankassessment.datasource.local.PaymentsLocalDataSource
import com.bendrisstarek.rabobankassessment.datasource.local.entity.Payment
import com.bendrisstarek.rabobankassessment.datasource.remote.PaymentsRemoteDataSource
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * this class represents the user's repository
 */
@Singleton
class PaymentsRepository @Inject internal constructor(private val paymentsLocalDataSource: PaymentsLocalDataSource, private val paymentsRemoteDataSource: PaymentsRemoteDataSource) {


    /**
     * this functions calls the remote datasource to get a list of payments
     */
    fun getUserPayments(userId: String?): Single<List<Payment>?>? {
        return paymentsRemoteDataSource.getUserPayments(userId)
                ?.doOnSuccess { response: List<Payment>? -> }
    }



}