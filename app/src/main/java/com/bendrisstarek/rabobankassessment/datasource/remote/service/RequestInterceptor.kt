package com.bendrisstarek.rabobankassessment.datasource.remote.service

import android.os.Build
import android.preference.PreferenceManager
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import com.bendrisstarek.rabobankassessment.App
import com.bendrisstarek.rabobankassessment.datasource.local.PaymentsLocalDataSource
import okhttp3.*
import java.io.IOException
import java.security.*
import java.security.cert.Certificate
import java.security.spec.PKCS8EncodedKeySpec
import javax.inject.Inject
import kotlin.math.sign

/**
 * this class represents and configures the request interceptor's module
 */
class RequestInterceptor internal constructor() : Interceptor {
    @JvmField
    @Inject
    var paymentsLocalDataSource: PaymentsLocalDataSource? = null

    /**
     * this function intercepts every API call
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val sharedpreferences = PreferenceManager.getDefaultSharedPreferences(App.getContext())
        val t = sharedpreferences.getString("Token", "")
        var token: String
        token = "Bearer $t"
        val request = chain.request()
        val headers = provideHeaders(token)
        val httpUrl = provideHttpUrl(request)
        val newRequest = provideRequest(request, headers, httpUrl)
        return chain.proceed(newRequest)
    }

    companion object {
        /*Params*/
        private const val PAGE = "page"
        private const val API_KEY = "api_key"
        private const val LANGUAGE = "language"

        /* Headers */
        private const val CONTENT_TYPE = "Content-Type"
        private const val AUTHORIZATION = "Authorization"
        private const val XBunqClientAuthentication = "X-Bunq-Client-Authentication"
        private const val XBunqClientSignature = "X-Bunq-Client-Signature"
        private const val XBunqClientRequestId = "X-Bunq-Client-Request-Id"
        private const val ACCEPT = "accept"
        private const val ACCEPT_ENCODING = "accept-encoding"
        private const val ACCEPT_LANGUAGE = "accept-language"
        private const val USER_AGENT = "user-agent"

        /* Values */
        private const val CONTENT_JSON = "application/json"
        private const val LANGUAGE_EN = "en-US,en;q=0.8"

        fun provideRequest(original: Request, headers: Headers, httpUrl: HttpUrl): Request {
            val requestBuilder = original.newBuilder()
                    .headers(headers) //.url(httpUrl)
                    .method(original.method, original.body)
            return requestBuilder.build()
        }

        /**
         * in every API request interception, we provide headers.
         */
        @RequiresApi(api = Build.VERSION_CODES.O)
        fun provideHeaders(token: String?): Headers {
            val headersBuilder = Headers.Builder()
            headersBuilder.add(AUTHORIZATION, token!!)
            headersBuilder.add(ACCEPT, CONTENT_JSON)
            headersBuilder.add(CONTENT_TYPE, "$CONTENT_JSON;charset=UTF-8")
            headersBuilder.add(ACCEPT_LANGUAGE, LANGUAGE_EN)
            return headersBuilder.build()
        }


        fun provideHttpUrl(original: Request): HttpUrl {
            val httpUrlBuilder = original.url.newBuilder()
                    .addQueryParameter(LANGUAGE, LANGUAGE_EN)
            return httpUrlBuilder.build()
        }
    }

    init {
        App.getDataComponent().inject(this)
    }
}