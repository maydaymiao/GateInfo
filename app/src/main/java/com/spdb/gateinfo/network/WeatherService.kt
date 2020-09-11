package com.spdb.gateinfo.network

import com.spdb.gateinfo.GateInfoApplication
import com.spdb.gateinfo.model.DailyResponse
import com.spdb.gateinfo.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
//    实时天气
    @GET("v2.5/${GateInfoApplication.TOKEN_CAIYUN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<RealtimeResponse>

//    未来天气预报
    @GET("v2.5/${GateInfoApplication.TOKEN_CAIYUN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>
}