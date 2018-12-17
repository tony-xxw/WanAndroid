package com.study.kotlin.wan.ui


import android.util.Log
import com.koltin.common.base.BaseActivity
import com.study.kotlin.wan.R


class MainActivity : BaseActivity() {
    override fun initView() {
        Log.d("XXW", "www");
    }

    override fun getLayoutId(): Int = R.layout.activity_main
}
