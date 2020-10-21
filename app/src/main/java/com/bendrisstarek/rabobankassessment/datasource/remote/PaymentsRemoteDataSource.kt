package com.bendrisstarek.rabobankassessment.datasource.remote

import com.bendrisstarek.rabobankassessment.datasource.local.entity.Payment
import com.bendrisstarek.rabobankassessment.datasource.remote.service.ServiceEndpoint
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * this class represents the payments' remote datasource
 */
@Singleton
class PaymentsRemoteDataSource @Inject constructor(private val serviceEndpoint: ServiceEndpoint) {



    /**
     * this function calls the endpoint to get a list of payments
     */
    fun getUserPayments(userId: String?): Single<List<Payment>?>? {
        return serviceEndpoint.getPayments(userId)
    }



}