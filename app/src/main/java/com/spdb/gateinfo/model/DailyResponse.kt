package com.spdb.gateinfo.model

import com.google.gson.annotations.SerializedName
import java.util.*

//https://api.caiyunapp.com/v2.5/dGzJn6htvX8qd8qy/114.06455183658751,22.548456637984177/weather.json

data class DailyResponse(
    @SerializedName("result")
    val result: Result,
    @SerializedName("status")
    val status: String
){
    data class Result(
        @SerializedName("daily")
        val daily: Daily
    )

    data class Daily(
        @SerializedName("skycon")
        val skycon: List<Skycon>,
        @SerializedName("temperature")
        val temperature: List<Temperature>
    )


    data class Skycon(
        @SerializedName("date")
        val date: Date,
        @SerializedName("value")
        val value: String
    )

    data class Temperature(
        @SerializedName("max")
        val max: Double,
        @SerializedName("min")
        val min: Double
    )

}
