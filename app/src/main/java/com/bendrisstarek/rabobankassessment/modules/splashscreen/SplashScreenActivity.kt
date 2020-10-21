package com.bendrisstarek.rabobankassessment.modules.splashscreen

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityOptionsCompat
import com.bendrisstarek.rabobankassessment.base.BaseActivity
import com.bendrisstarek.rabobankassessment.modules.main.MainActivity
import com.bendrisstarek.rabobankassessment.R

/**
 * this activity is the first one to start, it is the splashscreen
 */
class SplashScreenActivity : BaseActivity() {

    private var preferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    private val SPLASH_DISPLAY_LENGTH = 3000
    private var handler: Handler? = null
    private var run: Runnable? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash_screen)

        preferences = PreferenceManager.getDefaultSharedPreferences(this)
        editor = preferences?.edit()

        initVars()

    }

    private fun initVars()
    {
        val logoImg:ImageView = findViewById(R.id.logoImg)

        /**
         * after 3 seconds, the next activity will appear
         */
        run = Runnable {
            val mainIntent = Intent(this, MainActivity::class.java)

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                logoImg,
                logoImg.transitionName
            )
            startActivity(mainIntent, options.toBundle());
            this.finish()
        }

        handler = Handler()
        handler?.postDelayed(
                run!!
                , SPLASH_DISPLAY_LENGTH.toLong())
    }

    /**
     * to avoid starting the other intent when the splash screen is onPause or onStop, we remove the callbacks
     */
    override fun onPause() {
        super.onPause()
        handler?.removeCallbacks(run!!)
    }

    override fun onStop() {
        super.onStop()
        handler?.removeCallbacks(run!!)
    }

    override fun onRestart() {
        super.onRestart()
        handler?.postDelayed(run!!, SPLASH_DISPLAY_LENGTH.toLong())
    }
}

