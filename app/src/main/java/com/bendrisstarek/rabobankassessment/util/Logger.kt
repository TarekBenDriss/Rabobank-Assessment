package com.bendrisstarek.rabobankassessment.util

import android.util.Log

/**
 * this class represents a logger
 */
object Logger {
    private const val APP_TAG = ApplicationUtils.NAME + ":"
    @JvmStatic
    fun <T> i(tag: String, msg: T) {
        Log.i(APP_TAG + tag, msg.toString() + "")
    }

    @JvmStatic
    fun <T> d(tag: String, msg: T) {
        Log.d(APP_TAG + tag, msg.toString() + "")
    }

    @JvmStatic
    fun <T> v(tag: String, msg: T) {
        Log.v(APP_TAG + tag, msg.toString() + "")
    }

    @JvmStatic
    fun <T> e(tag: String, msg: T) {
        Log.e(APP_TAG + tag, msg.toString() + "")
    }

    @JvmStatic
    fun <T> w(tag: String, msg: T) {
        Log.w(APP_TAG + tag, msg.toString() + "")
    }

    @JvmStatic
    fun <T> wtf(tag: String, msg: T) {
        Log.wtf(APP_TAG + tag, msg.toString() + "")
    }

    @JvmStatic
    fun <T> i(msg: T) {
        Log.i(ApplicationUtils.NAME, msg.toString() + "")
    }

    @JvmStatic
    fun <T> d(msg: T) {
        Log.d(ApplicationUtils.NAME, msg.toString() + "")
    }

    @JvmStatic
    fun <T> v(msg: T) {
        Log.v(ApplicationUtils.NAME, msg.toString() + "")
    }

    @JvmStatic
    fun <T> e(msg: T) {
        Log.e(ApplicationUtils.NAME, msg.toString() + "")
    }

    @JvmStatic
    fun <T> w(msg: T) {
        Log.w(ApplicationUtils.NAME, msg.toString() + "")
    }

    @JvmStatic
    fun <T> wtf(msg: T) {
        Log.wtf(ApplicationUtils.NAME, msg.toString() + "")
    }

    @JvmStatic
    fun error(tag: Any, throwable: Throwable?) {
        Log.e(APP_TAG + tag.javaClass.simpleName, ExceptionUtils.format(throwable))
    }

    @JvmStatic
    fun error(tag: String, throwable: Throwable?) {
        Log.e(APP_TAG + tag, ExceptionUtils.format(throwable))
    }
}