package com.bendrisstarek.rabobankassessment

import android.content.Context
import android.content.res.Resources
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.Charset

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTestCases {

    private lateinit var instrumentationContext: Context


    @Before
    fun setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = instrumentationContext
        assertEquals("com.bendrisstarek.rabobankassessment", appContext.packageName)
    }


}