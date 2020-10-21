package com.bendrisstarek.rabobankassessment.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * This class is used to declare some functions for the internet connection
 */
object ConnectionUtils {
    /**
     * This function will check the state of the internet connection
     *
     * @param context the context of the activity
     * @return true if the internet is ok, and false is not
     */
    fun checkConnection(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).state == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).state == NetworkInfo.State.CONNECTED
    }
}