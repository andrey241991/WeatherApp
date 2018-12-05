package com.example.home_pc.myrxexample.api

data class WeatherData(
        val city: City,
        val cnt: Int,
        val cod: String,
        val list: List<WeatherList>,
        val message: Double
)

data class WeatherList(
        val clouds: Clouds,
        val dt: Int,
        val dt_txt: String,
        val main: Main,
        val rain: Rain,
        val sys:  Sys,
        val weather:  List<Weather>,
        val wind: Wind
)

data class Rain(
        val h: String
)

data class Weather(
        val description: String,
        val icon: String,
        val id: Int,
        val main: String
)

data class Main(
        val grnd_level: Double,
        val humidity: Int,
        val pressure: Double,
        val sea_level: Double,
        val temp: Double,
        val temp_kf: Double,
        val temp_max: Double,
        val temp_min: Double
)

data class Sys(
        val pod: String
)

data class Wind(
        val deg: Double,
        val speed: Double
)

data class Clouds(
        val all: Int
)

data class City(
        val coord: Coord,
        val country: String,
        val id: Int,
        val name: String,
        val population: Int
)

data class Coord(
        val lat: Double,
        val lon: Double
)