package com.example.home_pc.myrxexample.main.view

import com.arellomobile.mvp.MvpView
import com.example.home_pc.myrxexample.api.WeatherList
import com.example.home_pc.myrxexample.util.ProgressView

interface MainView : MvpView, ProgressView {
    fun changeMetric()
    fun showNetworkError()
    fun setDate(weatherList: ArrayList<WeatherList>)
    fun setTemperature(weatherList:WeatherList)
    fun getNetworkState()
    fun getMetric()
    fun showCelciusSign()
    fun showFahrenheitsSign()
}