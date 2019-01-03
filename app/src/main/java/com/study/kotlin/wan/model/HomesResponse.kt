package com.study.kotlin.wan.model

data class HomesResponse<T>(
        var offset: Int,
        var size: Int,
        var total: Int,
        var pageCount: Int,
        var curPage: Int,
        var over: Boolean,
        var datas: List<T>?
)