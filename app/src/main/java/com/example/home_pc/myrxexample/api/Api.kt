package com.example.home_pc.myrxexample.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    @GET("/data/2.5/forecast?")
    fun getWeatherDate(@Query("q") city: String,
                        @Query("mode") mode: String,
                       @Query("units") units: String,
                       @Query("APPID") appid: String
    ):Call<WeatherData>
}