package com.study.kotlin.wan.net

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.study.kotlin.wan.constant.Constant
import com.study.kotlin.wan.util.PreferenceUtil
import com.study.kotlin.wan.util.loge
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {
    private const val TAG = "RetrofitHelper"
    private const val CONNECT_TIMEOUT = 30L
    private const val READ_TIMEOUT = 10L
    private const val SAVE_USER_LOGIN_KEY = "user/login"
    private const val SAVE_USER_REGISTER_KEY = "user/register"
    private const val SET_COOKIE_KEY = "set-cookie"
    private const val COOKIE_NAME = "Cookie"
    private const val CONTENT_PRE = "Retrofit: "

    val retrofitHelper: RetrofitService =
            RetrofitHelper.getService(url = Constant.BASE_URl, service = RetrofitService::class.java)


    private fun create(url: String): Retrofit {
        var okhttpClientBuilder = OkHttpClient().newBuilder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            addInterceptor {
                val request = it.request()
                val response = it.proceed(request)
                val requestUrl = request.url().toString()
                val domain = request.url().host()
                if ((requestUrl.contains(SAVE_USER_LOGIN_KEY) || requestUrl.contains(SAVE_USER_REGISTER_KEY))
                        && !response.headers(SET_COOKIE_KEY).isEmpty()) {
                    val cookies = response.header(SET_COOKIE_KEY)
                    val cookie = encodeCookie(cookies)
                    saveCooking(requestUrl, domain, cookie)
                }
                response
            }
            addInterceptor {
                val request = it.request()
                val builder = request.newBuilder()
                val domain = request.url().host()

                if (domain.isNotEmpty()) {
                    val spDomain: String by PreferenceUtil(domain, "")
                    val cookie: String = if (spDomain.isNotEmpty()) spDomain else ""
                    if (cookie.isNotEmpty()) {
                        builder.addHeader(COOKIE_NAME, cookie)
                    }
                }
                it.proceed(builder.build())
            }


            if (Constant.INTERCEPTOR_ENABLE) {
                addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                    loge(TAG, CONTENT_PRE)
                }).apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
        }

        return RetrofitBuild(url = url, client = okhttpClientBuilder.build(),
                gsonFactory = GsonConverterFactory.create(),
                coroutineCallAdapterFactory = CoroutineCallAdapterFactory())
                .retrofit
    }

    private fun saveCooking(requestUrl: String, domain: String?, cookie: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun encodeCookie(cookies: String?): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun <T> getService(url: String, service: Class<T>): T = create(url).create(service)
}


class RetrofitBuild(url: String, client: OkHttpClient,
                    gsonFactory: GsonConverterFactory,
                    coroutineCallAdapterFactory: CoroutineCallAdapterFactory) {
    val retrofit: Retrofit = Retrofit.Builder().apply {
        baseUrl(url)
        client(client)
        addConverterFactory(gsonFactory)
        addCallAdapterFactory(coroutineCallAdapterFactory)
    }.build()
}