package com.spdb.gateinfo.ui.weather

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.spdb.gateinfo.GateInfoApplication
import com.spdb.gateinfo.R
import com.spdb.gateinfo.model.Weather
import com.spdb.gateinfo.model.getSky
import com.spdb.gateinfo.network.MyWorker
import kotlinx.android.synthetic.main.fragment_weather.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class WeatherFragment : Fragment() {
    val viewModelPlace by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }
    val viewModelWeather by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }
    private val TAG = "myTag"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        调用workmanager，定时刷新
        val request = PeriodicWorkRequest.Builder(MyWorker::class.java,15,TimeUnit.MINUTES).build()
        WorkManager.getInstance().enqueue(request)

        viewModelPlace.placeLiveData.observe(viewLifecycleOwner, Observer { result ->
            val place = result.getOrNull()
//             Result(location=Location(lat=22.548456637984177, lng=114.06455183658751))
            if (place != null) {
                val lat = place.location.lat.toString()
                val lng = place.location.lng.toString()
                Log.d("myTag", "${lat}, $lng")
                viewModelWeather.locationLng = lng
                viewModelWeather.locationLat = lat
//                获得经纬度后，刷新天气
                viewModelWeather.refreshWeather(viewModelWeather.locationLng, viewModelWeather.locationLat)
            } else {
                Toast.makeText(activity, "未能查询到任何地点", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        viewModelWeather.weatherLiveData.observe(viewLifecycleOwner, Observer { result ->
            val weather = result.getOrNull()
            if (weather != null) {
                Log.d(TAG, weather.toString())
                showWeatherInfo(weather)
            }else {
                Toast.makeText(activity, "无法获取天气信息", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

    }

//    在UI展示信息
    private fun showWeatherInfo(weather: Weather){
        val realtime = weather.realtime
        val daily = weather.dailyResponse
//        获取当前天气
        val currentTempText = "${realtime.temperature.toInt()} °"
        currentTemp.text = currentTempText
        val currentHumText = "湿度 ${((realtime.humidity)*100).toInt()} %"
        currentHum.text = currentHumText
//        AQI
        val currentAQIValue = realtime.airQuality.description.chn.toString()
        val currentAQIText = "AQI  ${currentAQIValue}"
        currentAQI.text = currentAQIText
        when(currentAQIValue){
            "优" -> currentAQI.setTextColor(Color.GREEN)
            "良" -> currentAQI.setTextColor(Color.YELLOW)
            else -> currentAQI.setTextColor(Color.RED)
        }

        val currentSky = getSky(realtime.skycon)
        currentSkyIcon.setImageResource(currentSky.icon)

        // 填充forecast.xml布局中的数据
        forecastLayout.removeAllViews()
        val days = daily.skycon.size
        for(i in 1 until days){
            val skycon = daily.skycon[i]
            val temperature = daily.temperature[i]
//            把forecast_item加入到forecastLayout(LinearLayout)里
            val view = LayoutInflater.from(activity).inflate(R.layout.forecast_item, forecastLayout, false)
            val dateInfo = view.findViewById(R.id.dateInfo) as TextView
            val skyIcon = view.findViewById(R.id.skyIcon) as ImageView
            val skyInfo = view.findViewById(R.id.skyInfo) as TextView
            val temperatureInfo = view.findViewById(R.id.temperatureInfo) as TextView
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            dateInfo.text = simpleDateFormat.format(skycon.date)
            val sky = getSky(skycon.value)
            skyIcon.setImageResource(sky.icon)
            skyInfo.text = sky.info
            val tempText = "${temperature.min.toInt()} ~ ${temperature.max.toInt()} ℃"
            temperatureInfo.text = tempText
            forecastLayout.addView(view)
        }
    }
}
