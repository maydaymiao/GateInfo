package com.spdb.gateinfo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL_BAIDU = "http://api.map.baidu.com/"
    private const val BASE_URL_CAIYUN = "https://api.caiyunapp.com/"

    private val retrofit_baidu = Retrofit.Builder().baseUrl(BASE_URL_BAIDU).addConverterFactory(GsonConverterFactory.create()).build()
    private val retrofit_caiyun = Retrofit.Builder().baseUrl(BASE_URL_CAIYUN).addConverterFactory(GsonConverterFactory.create()).build()

    fun <T> createBaidu(serviceClass: Class<T>): T = retrofit_baidu.create(serviceClass)
    fun <T> createCaiyun(serviceClass: Class<T>): T = retrofit_caiyun.create(serviceClass)

    inline fun <reified T> createBaidu(): T = createBaidu(T::class.java)
    inline fun <reified T> createCaiyun(): T = createCaiyun(T::class.java)
}