package com.bendrisstarek.rabobankassessment.util

import android.app.FragmentManager
import android.view.Gravity
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

/**
 * this class represents a fragments utils
 */
object FragmentUtils {
    private fun <T> huskFragment(instance: Class<T>, `object`: Any?): T? {
        if (`object` == null) {
            return null
        }
        return if (`object`.javaClass == instance) {
            `object` as T
        } else null
    }

    fun <T> huskFragment(instance: Class<T>, id: Int, activity: FragmentActivity): T? {
        return huskFragment(instance, activity.supportFragmentManager.findFragmentById(id))
    }

    fun <T> huskFragment(instance: Class<T>, id: Int, childFragmentManager: FragmentManager): T? {
        return huskFragment(instance, childFragmentManager.findFragmentById(id))
    }

    fun <T> huskFragmentOrCreate(instance: Class<T>, id: Int, childFragmentManager: FragmentManager): T {
        val fragment = huskFragment(instance, childFragmentManager.findFragmentById(id))
        return fragment
                ?: try {
                    instance.newInstance()
                } catch (ex: Exception) {
                    throw RuntimeException("Fix fragment husk!")
                }
    }

    fun <T> huskFragment(instance: Class<T>, tag: String?, activity: FragmentActivity): T? {
        return huskFragment(instance, activity.supportFragmentManager.findFragmentByTag(tag))
    }

    fun <T> huskFragment(instance: Class<T>, tag: String?, childFragmentManager: FragmentManager): T? {
        return huskFragment(instance, childFragmentManager.findFragmentByTag(tag))
    }

    @JvmStatic
    fun setWindowFullSize(fragment: DialogFragment) {
        val window = fragment.dialog!!.window
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
            window.setGravity(Gravity.CENTER)
        }
    }
}