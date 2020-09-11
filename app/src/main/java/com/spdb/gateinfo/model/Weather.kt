package com.spdb.gateinfo.model

data class Weather(val realtime: RealtimeResponse.Realtime, val dailyResponse: DailyResponse.Daily)