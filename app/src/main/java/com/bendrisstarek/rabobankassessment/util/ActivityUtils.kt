package com.bendrisstarek.rabobankassessment.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import com.bendrisstarek.rabobankassessment.base.BaseActivity


/**
 * this class reperents an utils for activities
 */
class ActivityUtils {

    companion object {
        @JvmStatic
        fun getManager(context: Context?): FragmentManager? {
            return if (context != null) {
                try {
                    (context as BaseActivity).supportFragmentManager
                } catch (error: Exception) {
                    null
                }
            } else null
        }

        /**
         * this function hides the status bar
         * @param activity
         */
        @JvmStatic
        fun hideStatusBar(activity: Activity) { // Hide the Action Bar
            getWindow(activity).setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        /**
         * this function shows the status bar
         * @param activity
         */
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        fun showStatusBar(activity: Activity) {
            getWindow(activity).setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
        }

        /**
         * this function returns the activity's window
         * @param activity
         * @return
         */
        private fun getWindow(@NonNull activity: Activity): Window {
            return activity.window
        }

        /**
         * this function returns the activity's root view
         * @param activity
         * @return
         */
        private fun getRootView(@NonNull activity: Activity): View {
            return getWindow(activity).decorView.rootView
        }

        @JvmStatic
        fun setFullScreenMode(activity: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                activity.window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            }
            fullScreenCall(activity)
            registerSystemUiVisibility(activity)
        }


        private fun fullScreenCall(activity: Activity) {
            if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
                val v = activity.window.decorView
                v.systemUiVisibility = View.GONE
            } else if (Build.VERSION.SDK_INT >= 19) {
                //for new api versions.
                val decorView = activity.window.decorView
                val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                decorView.systemUiVisibility = uiOptions
            }
        }


        private fun registerSystemUiVisibility(activity: Activity) {
            val decorView = activity.window.decorView
            decorView.setOnSystemUiVisibilityChangeListener { visibility ->
                if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                    val handler = Handler()
                    handler.postDelayed({ fullScreenCall(activity) }, 2000)
                }
            }
        }


    }







}
