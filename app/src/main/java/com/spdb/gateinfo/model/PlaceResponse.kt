package com.spdb.gateinfo.model
import com.google.gson.annotations.SerializedName


// 获取经纬度：http://api.map.baidu.com/geocoding/v3/?address=%E4%B8%8A%E6%B5%B7&output=json&ak=gzfQV32sKU4cmg4jKgybuQBt666W60s9&callback=showLocation

data class PlaceResponse(
    @SerializedName("result")
    val result: Result,
    @SerializedName("status")
    val status: Int
)

data class Result(
    @SerializedName("location")
    val location: Location,
)

data class Location(
    @SerializedName("lng")
    val lng: String,
    @SerializedName("lat")
    val lat: String
)

//{
//    status: 0,
//    result: {
//    location: {
//            lng: 121.48053886017651,
//            lat: 31.235929042252014
//    },
//    precise: 0,
//    confidence: 20,
//    comprehension: 100,
//    level: "城市"
//}
//}
