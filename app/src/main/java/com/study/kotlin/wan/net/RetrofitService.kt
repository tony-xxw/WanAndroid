package com.study.kotlin.wan.net

import com.study.kotlin.wan.model.Blog
import com.study.kotlin.wan.model.HomesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("/article/list/{page}/json")
    fun obtainBlogs(@Path("page") page: Int): Deferred<HomesResponse<Blog>>
}