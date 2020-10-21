package com.bendrisstarek.rabobankassessment.datasource.local.database;



import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.bendrisstarek.rabobankassessment.datasource.local.converter.Converters;
import com.bendrisstarek.rabobankassessment.datasource.local.dao.PaymentDAO;
import com.bendrisstarek.rabobankassessment.datasource.local.entity.Payment;
import com.bendrisstarek.rabobankassessment.util.ApplicationUtils;

/**
 * this class configures the room database
 */
@Database(entities = {Payment.class}, version = AppDatabase.VERSION, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    static final int VERSION = 1;
    static final String NAME = ApplicationUtils.NAME + ".db";

    public abstract PaymentDAO getPaymentDao();

}