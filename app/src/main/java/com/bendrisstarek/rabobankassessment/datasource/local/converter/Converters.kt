package com.bendrisstarek.rabobankassessment.datasource.local.converter

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * this class provides converters for many types
 */
object Converters {
    /**
     * this function converts a timestamp to date
     * @param timestamp
     * @return
     */
    @JvmStatic
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    /**
     * this function converts a string to a date
     * @param dateStr
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    @TypeConverter
    fun stringToDate(dateStr: String?): Date? {
        var date: Date? = null
        val formatter: DateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy")
        try {
            date = formatter.parse(dateStr) as Date
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date
    }

    /**
     * this function converts a date to a timestamp
     * @param date
     * @return
     */
    @JvmStatic
    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }

    /**
     * this function converts a string to a list
     * @param str
     * @return
     */
    @TypeConverter
    fun toList(str: String?): List<Long?>? {
        return try {
            ObjectMapper().readValue<List<Long?>>(str, object : TypeReference<List<Long?>?>() {})
        } catch (error: IOException) {
            null
        }
    }

    /**
     * this function converts a list to a string
     * @param list
     * @return
     */
    @TypeConverter
    fun toString(list: List<Long?>?): String? {
        return if (list != null) {
            try {
                ObjectMapper().writeValueAsString(list)
            } catch (e: IOException) {
                null
            }
        } else {
            null
        }
    }
}