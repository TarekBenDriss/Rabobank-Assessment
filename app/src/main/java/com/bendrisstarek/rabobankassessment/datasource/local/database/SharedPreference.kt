package com.bendrisstarek.rabobankassessment.datasource.local.database

import android.content.Context
import android.content.SharedPreferences
import com.bendrisstarek.rabobankassessment.util.ApplicationUtils
import com.bendrisstarek.rabobankassessment.util.StringUtils.isNotEmpty
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException

/**
 * this class implements a custom shared preferences
 */
class SharedPreference(context: Context, objectMapper: ObjectMapper) {
    /**
     * this function returns the shared preferences
     * @return
     */
    private val sharedPreferences: SharedPreferences
    private val objectMapper: ObjectMapper

    /**
     * this function puts a boolean
     * @param key
     * @param value
     */
    fun putBoolean(key: String, value: Boolean) {
        val edit = sharedPreferences.edit()
        edit.putBoolean(key, value)
        edit.apply()
    }

    /**
     * this function puts a float
     * @param key
     * @param value
     */
    fun putFloat(key: String, value: Float) {
        val edit = sharedPreferences.edit()
        edit.putFloat(key, value)
        edit.apply()
    }

    /**
     * this function puts an int
     * @param key
     * @param value
     */
    fun putInt(key: String, value: Int) {
        val edit = sharedPreferences.edit()
        edit.putInt(key, value)
        edit.apply()
    }

    /**
     * this function puts a long
     * @param key
     * @param value
     */
    fun putLong(key: String, value: Long) {
        val edit = sharedPreferences.edit()
        edit.putLong(key, value)
        edit.apply()
    }

    /**
     * this function puts a double
     * @param key
     * @param value
     */
    fun putDouble(key: String, value: Double) {
        val edit = sharedPreferences.edit()
        edit.putString(key, value.toString())
        edit.apply()
    }

    /**
     * this function puts a string
     * @param key
     * @param value
     */
    fun putString(key: String, value: String?) {
        val edit = sharedPreferences.edit()
        edit.putString(key, value)
        edit.apply()
    }

    /**
     * this function puts an object
     * @param key
     * @param object
     * @param <T>
    </T> */
    fun <T> putObject(key: String, `object`: T) {
        val edit = sharedPreferences.edit()
        val value = convertToString(`object`)
        edit.putString(key, value)
        edit.apply()
    }

    /**
     * this function puts a stringset
     * @param key
     * @param value
     */
    fun putStringSet(key: String, value: Set<String?>?) {
        val edit = sharedPreferences.edit()
        edit.putStringSet(key, value)
        edit.apply()
    }

    /**
     * this function returns a boolean from a key
     * @param key
     * @param defValue
     * @return
     */
    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    /**
     * this function returns a float from a key
     * @param key
     * @param defValue
     * @return
     */
    fun getFloat(key: String, defValue: Float): Float {
        return sharedPreferences.getFloat(key, defValue)
    }

    /**
     * this function returns a double from a key
     * @param key
     * @param defValue
     * @return
     */
    fun getDouble(key: String, defValue: Double): Double {
        val valueStr = sharedPreferences.getString(key, defValue.toString())
        return if (isNotEmpty(valueStr)) valueStr!!.toDouble() else 0.0
    }

    /**
     * this function returns an int from a key
     * @param key
     * @param defValue
     * @return
     */
    fun getInt(key: String, defValue: Int): Int {
        return sharedPreferences.getInt(key, defValue)
    }

    /**
     * this function returns a long from a keyu
     * @param key
     * @param defValue
     * @return
     */
    fun getLong(key: String, defValue: Long): Long {
        return sharedPreferences.getLong(key, defValue)
    }

    /**
     * this function returns a string from a key
     * @param key
     * @param defValue
     * @return
     */
    fun getString(key: String, defValue: String?): String? {
        return sharedPreferences.getString(key, defValue)
    }

    /**
     * this function returns a string set
     * @param key
     * @param defValue
     * @return
     */
    fun getStringSet(key: String, defValue: Set<String?>?): Set<String>? {
        return sharedPreferences.getStringSet(key, defValue)
    }

    /**
     * this function gets an object from a key
     * @param key
     * @param valueType
     * @param <T>
     * @return
    </T> */
    fun <T> getObject(key: String, valueType: Class<T>): T? {
        val str = sharedPreferences.getString(key, null)
        return if (isNotEmpty(str)) convertToObject(str, valueType) else null
    }

    /**
     * this function checks if the shared preferences contains a key
     * @param key
     * @return
     */
    operator fun contains(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    /**
     * this function delets a key
     * @param key
     */
    fun remove(key: String) {
        val edit = sharedPreferences.edit()
        edit.remove(key)
        edit.apply()
    }

    /**
     * this function clear all the shared preferences
     */
    fun clearAll() {
        val preferences = sharedPreferences.all
        val edit = sharedPreferences.edit()
        for ((key) in preferences) {
            edit.remove(key)
        }
        edit.apply()
    }

    /**
     * this function converts a string to an object
     * @param src
     * @param valueType
     * @param <T>
     * @return
    </T> */
    private fun <T> convertToObject(src: String?, valueType: Class<T>): T? {
        return try {
            objectMapper.readValue(src, valueType)
        } catch (error: IOException) {
            null
        }
    }

    /**
     * this function converts an object to a string
     * @param value
     * @param <T>
     * @return
    </T> */
    private fun <T> convertToString(value: T?): String? {
        return if (value != null) {
            try {
                objectMapper.writeValueAsString(value)
            } catch (e: IOException) {
                null
            }
        } else {
            null
        }
    }

    companion object {
        private const val NAME = "SharedPreferences"
    }

    init {
        sharedPreferences = context.getSharedPreferences(ApplicationUtils.NAME + NAME, Context.MODE_PRIVATE)
        this.objectMapper = objectMapper
    }
}