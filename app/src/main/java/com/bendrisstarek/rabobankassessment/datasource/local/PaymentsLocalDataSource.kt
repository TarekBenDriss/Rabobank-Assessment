package com.bendrisstarek.rabobankassessment.datasource.local

import androidx.lifecycle.LiveData
import com.bendrisstarek.rabobankassessment.datasource.local.database.AppDatabase
import com.bendrisstarek.rabobankassessment.datasource.local.entity.Payment
import javax.inject.Inject
import javax.inject.Singleton

/**
 * this class represents the payments local datasource
 */
@Singleton
class PaymentsLocalDataSource @Inject constructor(private val database: AppDatabase) {

    /**
     * this function adds a payment to the database
     * @param user
     */
    fun addPayment(user: Payment?) {
        database.paymentDao.insert(user)
    }

    /**
     * this function deletes all the payments from the database
     */
    fun delete() {
        database.paymentDao.delete()
    }

}