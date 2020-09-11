package com.spdb.gateinfo.network

import com.spdb.gateinfo.GateInfoApplication
import com.spdb.gateinfo.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET

// http://api.map.baidu.com/geocoding/v3/?address=%E4%B8%8A%E6%B5%B7&output=json&ak=gzfQV32sKU4cmg4jKgybuQBt666W60s9

interface PlaceService {
    @GET("geocoding/v3/?address=${GateInfoApplication.LOCATION}&output=json&ak=${GateInfoApplication.TOKEN_BAIDU}")
    fun searchPlace(): Call<PlaceResponse>
}