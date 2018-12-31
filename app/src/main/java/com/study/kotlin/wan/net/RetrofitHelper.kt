package com.study.kotlin.wan.net

import android.preference.Preference
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.study.kotlin.wan.constant.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {

    private const val CONNECT_TIMEOUT = 30L
    private const val READ_TIMEOUT = 10L
    private const val SAVE_USER_LOGIN_KEY = "user/login"
    private const val SAVE_USER_REGISTER_KEY = "user/register"
    private const val SET_COOKIE_KEY = "set-cookie"
    private const val COOKIE_NAME = "Cookie"

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
                if ((requestUrl.contains(SAVE_USER_LOGIN_KEY) || requestUrl.contains(SAVE_USER_REGISTER_KEY)) && !response.headers(SET_COOKIE_KEY).isEmpty()) {

                }
                response
            }
            TODO("2019/1/1 retrofit设置cook")

        }

        return RetrofitBuild(url = url, client = okhttpClientBuilder.build(),
                gsonFactory = GsonConverterFactory.create(),
                coroutineCallAdapterFactory = CoroutineCallAdapterFactory())
                .retrofit
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