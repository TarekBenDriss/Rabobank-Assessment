package com.bendrisstarek.rabobankassessment.datasource.remote.service


import com.bendrisstarek.rabobankassessment.datasource.local.entity.Payment
import io.reactivex.Single
import retrofit2.http.*

/**
 * this class represents the service endpoint
 */
interface ServiceEndpoint {


    /**
     * this function makes a new payment
     */
    @GET(GET_LIST_PAYMENTS)
    fun getPayments(@Path(USER_ID) userId: String?): Single<List<Payment>?>?


    companion object {
        const val BASE_URL = "https://baseurl-of-the-app.com"
        const val GET_LIST_PAYMENTS = "/payments/{user_id}"

        /*Params*/
        const val USER_ID = "user_id"
    }
}