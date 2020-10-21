package com.bendrisstarek.rabobankassessment.util

import android.text.TextUtils
import android.util.Log
import java.net.URLDecoder
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * this class represents string utils
 */
object StringUtils {
    const val UTF8 = "UTF-8"


    /**
     * this function returns the month abreviation from a number
     * @param i
     * @return
     */
    @JvmStatic
    fun transformToValidString(s: String): String {
        var result = s

        //Log.e("stringTag",s[s.length]+"")
        if(result[0] == '\"' && result[result.length-1] == '\"')
            result = result.substring(1,result.length-1)

        if(isValidDate(result)) {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val d = sdf.parse(result)
            sdf.applyPattern("yyyy-MM-dd")
            result = sdf.format(d!!)
        }

        return result
    }

    fun isValidDate(inDate: String): Boolean {
        val dateFormat =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
        dateFormat.isLenient = false
        try {
            dateFormat.parse(inDate.trim { it <= ' ' })
        } catch (pe: ParseException) {
            val dateFormat =
                SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss:ms", Locale.US)
            dateFormat.isLenient = false
            try {
                dateFormat.parse(inDate.trim { it <= ' ' })
            } catch (pe: ParseException) {
                return false
            }
        }
        return true
    }



    /**
     * this function returns the month abreviation from a number
     * @param i
     * @return
     */
    @JvmStatic
    fun getMonthFromNbr(i: Int): String {
        Log.e("MONTH", " $i")
        var s = ""
        when (i) {
            1 -> s = "JAN"
            2 -> s = "FEV"
            3 -> s = "MAR"
            4 -> s = "AVR"
            5 -> s = "MAI"
            6 -> s = "JUN"
            7 -> s = "JUL"
            8 -> s = "AOU"
            9 -> s = "SEP"
            10 -> s = "OCT"
            11 -> s = "NOV"
            12 -> s = "DEC"
        }
        return s
    }



    /**
     * this function returns if string is email or not
     * @param email
     * @return
     */
    @JvmStatic
    fun isEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".") && email.indexOf("@") != email.length && email.indexOf("@") != 0 && email.indexOf("@") != email.length - 1 && email.indexOf("@") != email.length - 2 && email.indexOf(".") != 0 && email.indexOf(".") != email.length && email.indexOf(".") != email.length - 1 && email.indexOf("@") != email.length - 3 && email.indexOf(".") != email.length - 2
    }

    /**
     * this function decodes the url
     * @param str
     * @return
     */
    @JvmStatic
    fun decodeURL(str: String?): String? {
        var decoded = str
        if (isNotEmpty(str)) {
            decoded = try {
                URLDecoder.decode(str, UTF8)
            } catch (e: Exception) {
                str
            }
        }
        return decoded
    }



    /**
     * this function returns a string with the first char is upper
     * @param str
     * @return
     */
    @JvmStatic
    fun upperFirstChar(str: String): String {
        return if (isNotEmpty(str)) str.substring(0, 1).toUpperCase() + str.substring(1) else str
    }

    /**
     * this function reutrn the uppered stirng
     * @param str
     * @return
     */
    @JvmStatic
    fun upperCase(str: String): String {
        return if (isNotEmpty(str)) str.toUpperCase() else str
    }

    /**
     * this function return the lowered string
     * @param str
     * @return
     */
    @JvmStatic
    fun lowerCase(str: String): String {
        return if (isNotEmpty(str)) str.toLowerCase() else str
    }

    /**
     * this function checks if string is empty or not
     * @param str
     * @return
     */
    @JvmStatic
    fun isEmpty(str: CharSequence?): Boolean {
        return TextUtils.isEmpty(str)
    }

    @JvmStatic
    fun isLength(str: CharSequence, i: Int): Boolean {
        return str.length == i
    }

    @JvmStatic
    fun isLengthOrGreater(str: CharSequence, i: Int): Boolean {
        return str.length >= i
    }

    /**
     * this function checks if two strings are equals
     * @param a
     * @param b
     * @return
     */
    @JvmStatic
    fun equals(a: CharSequence?, b: CharSequence?): Boolean {
        return TextUtils.equals(a, b)
    }

    /**
     * this function checks if char sequence is not empty
     * @param str
     * @return
     */
    @JvmStatic
    fun isNotEmpty(str: CharSequence?): Boolean {
        return !isEmpty(str)
    }

    /**
     * this function trims a string
     * @param str
     * @return
     */
    @JvmStatic
    fun trim(str: String): String {
        return if (isNotEmpty(str)) str.trim { it <= ' ' }.replace("\\s+".toRegex(), "") else str
    }

    /**
     * this function trims and remplaces a string
     * @param str
     * @return
     */
    @JvmStatic
    fun trimAndReplace(str: String): String {
        return if (isNotEmpty(str)) trim(str).replace("/", "") else str
    }
}