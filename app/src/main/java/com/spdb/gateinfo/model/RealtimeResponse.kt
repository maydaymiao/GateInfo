package com.spdb.gateinfo.model
import com.google.gson.annotations.SerializedName


//https://api.caiyunapp.com/v2.5/dGzJn6htvX8qd8qy/114.06455183658751,22.548456637984177/realtime.json
//{lng, lat }

data class RealtimeResponse(
    @SerializedName("result")
    val result: Result,
    @SerializedName("status")
    val status: String,

){
    data class Result(
        @SerializedName("realtime")
        val realtime: Realtime
    )

    data class Realtime(
        @SerializedName("air_quality")
        val airQuality: AirQuality,
        @SerializedName("humidity")
        val humidity: Double,
        @SerializedName("skycon")
        val skycon: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("temperature")
        val temperature: Double
    )

    data class AirQuality(
        @SerializedName("aqi")
        val aqi: Aqi,
        @SerializedName("pm25")
        val pm25: Double,
        @SerializedName("description")
        val description: Description
    )

    data class Aqi(
        @SerializedName("chn")
        val chn: Double,
        @SerializedName("usa")
        val usa: Double
    )

    data class Description(
        @SerializedName("chn")
        val chn: String
    )
}