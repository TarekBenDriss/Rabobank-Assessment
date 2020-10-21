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


@RunWith(AndroidJUnit4::class)
class CsvFileTest {

    private lateinit var instrumentationContext: Context
    private lateinit var instrumentationResource: Resources

    @Before
    fun setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext
        instrumentationResource = instrumentationContext.resources
    }

    /**
     * this function tests if the file exists or not
     * test files can be added to the assets folder
     */
    @Test
    @Throws(Exception::class)
    fun fileObjectShouldNotBeNull() {
        //val inputStream: InputStream = instrumentationContext.resources?.openRawResource(R.raw.issues)!!

        val inputStream = BufferedReader(
            InputStreamReader(instrumentationResource.assets.open("testFile.csv"))
        )

        assertThat(inputStream, CoreMatchers.notNullValue())
    }

    /**
     * this function check if the file is empty or not
     */
    @Test
    @Throws(Exception::class)
    fun fileIsNotEmpty() {
        val inputStream = BufferedReader(
            InputStreamReader(instrumentationResource.assets.open("testFile.csv"))
        )

        val reader = BufferedReader(inputStream)

        assertTrue(reader.readLines().isNotEmpty());
    }
}