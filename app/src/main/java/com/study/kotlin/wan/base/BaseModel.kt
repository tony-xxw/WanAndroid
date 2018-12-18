package com.study.kotlin.wan.base

data class BaseModel<T>(
        var data: T,
        var errorCode: Int,
        var errorMsg: String
)