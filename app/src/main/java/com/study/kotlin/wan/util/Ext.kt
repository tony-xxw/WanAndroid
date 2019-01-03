package com.study.kotlin.wan.util

import android.util.Log


fun loge(tag: String, content: String?) {
    //如果content为空就输出tag   xx ? 可以为Null
    Log.e(tag, content ?: tag)
}