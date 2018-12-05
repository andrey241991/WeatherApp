package com.example.home_pc.myrxexample.detailed.view.adapter

interface WeatherRepositoryRowView {
    fun setDate(date: String)
    fun setTime(time: String)
    fun setTemperature(temp: String)
    fun setWindSpeed(windSpeed: String)
}