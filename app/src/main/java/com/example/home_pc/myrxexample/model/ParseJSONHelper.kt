package com.example.home_pc.myrxexample.model

import com.example.home_pc.myrxexample.api.WeatherList

class ParseJSONHelper {

    companion object {

        val morning: String = "06:00:00"
        val day: String = "12:00:00"
        val evening: String = "21:00:00"

        fun getActualWeatherList(list: List<WeatherList>): ArrayList<WeatherList> {
            var newList = arrayListOf<WeatherList>()

            for (weatherList in list) {
                var dataString = weatherList.dt_txt
                when {
                    dataString.contains(morning) -> newList.add(weatherList)
                    dataString.contains(day) -> newList.add(weatherList)
                    dataString.contains(evening) -> newList.add(weatherList)
                }
            }
            return newList
        }
    }
}