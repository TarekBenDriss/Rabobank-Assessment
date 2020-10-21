package com.bendrisstarek.rabobankassessment.datasource.local.dao;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.bendrisstarek.rabobankassessment.datasource.local.entity.Payment;

import java.util.List;



/**
 * this class represents the payment's dao
 */
@Dao
public interface PaymentDAO {

    /**
     * this function adds a payment to the database
     * @param payment
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Payment payment);

    /**
     * this function adds a list of payments to the database
     * @param payments
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Payment> payments);

    /**
     * this function deletes a payment from the database
     */
    @Query("DELETE FROM Payment")
    void delete();

    /**
     * this function returns all the payments
     * @return List of payments
     */
    @Query("SELECT * FROM Payment")
    LiveData<Payment> findAll();


}