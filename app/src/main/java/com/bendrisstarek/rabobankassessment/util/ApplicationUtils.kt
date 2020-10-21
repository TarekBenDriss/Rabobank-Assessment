package com.bendrisstarek.rabobankassessment.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.provider.Settings.Secure

/**
 * this class represents application's utils
 */
object ApplicationUtils {
    const val NAME = "bunqAssessment"

    /**
     * this function returns the app package info
     * @param context
     * @return
     */
    private fun getPackageInfo(context: Context?): PackageInfo? {
        return if (context != null) {
            try {
                context.packageManager.getPackageInfo(context.packageName, 0)
            } catch (e: PackageManager.NameNotFoundException) {
                null
            }
        } else {
            null
        }
    }

    /**
     * this function returns the app version
     * @param context
     * @return
     */
    fun getVersion(context: Context?): String? {
        val packageInfo = getPackageInfo(context)
        return packageInfo?.versionName
    }

    /**
     * this function returns the device id
     * @param context
     * @return
     */
    @SuppressLint("HardwareIds")
    fun getDeviceID(context: Context?): String? {
        return if (context != null) Secure.getString(context.contentResolver, Secure.ANDROID_ID) else null
    }
}