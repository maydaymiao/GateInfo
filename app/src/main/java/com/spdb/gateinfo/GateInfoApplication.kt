package com.spdb.gateinfo

import android.app.Application
import android.content.Context

class GateInfoApplication: Application() {
    companion object{
        //        彩云API TOKEN
        const val TOKEN_CAIYUN = "dGzJn6htvX8qd8qy"
        const val TOKEN_BAIDU = "gzfQV32sKU4cmg4jKgybuQBt666W60s9"
        const val LOCATION = "深圳"
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}