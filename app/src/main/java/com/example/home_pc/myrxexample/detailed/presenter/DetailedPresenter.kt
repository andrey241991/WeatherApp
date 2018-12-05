package com.example.home_pc.myrxexample.detailed.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.home_pc.myrxexample.api.ApiClient
import com.example.home_pc.myrxexample.api.WeatherData
import com.example.home_pc.myrxexample.api.WeatherList
import com.example.home_pc.myrxexample.detailed.view.DetailedView
import com.example.home_pc.myrxexample.detailed.view.adapter.WeatherAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@InjectViewState
class DetailedPresenter : MvpPresenter<DetailedView>() {

    var networkAccess: Boolean = false
    lateinit var weatherList: ArrayList<WeatherList>

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.getNetworkState()
        getDateFromNetwork()
    }

    fun getDateFromNetwork() {
        viewState.showProgress()
        if (!networkAccess) {
            viewState.showNetworkError()
            return
        }

        val api = ApiClient()
        val client = api.getClient()
        val call = client.getWeatherDate("Cherkasy", "json", "metric", ApiClient.APP_ID)
        call.enqueue(object : Callback<WeatherData> {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                weatherList = response.body()!!.list as ArrayList<WeatherList>
                viewState.hideProgress()
                viewState.showData()
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                viewState.hideProgress()
                Log.v("TAG", "error =" + t.message);
            }

        })
    }

    fun getWetherListSize(): Int {
        return weatherList.size
    }

    fun onBindRepositoryRowViewAtPosition(position: Int, holder: WeatherAdapter.ViewHolder) {
        val currentWeather = weatherList.get(position)
        val parts = currentWeather.dt_txt.split(" ")
        val time = parts.get(1).substring(0, 5)
        holder.setDate(parts.get(0))
        holder.setTime(time)
        var temp = currentWeather.main.temp.toInt()
        when {
            temp > 0 -> holder.setTemperature("+" + temp.toString())
            else -> holder.setTemperature(temp.toString())
        }
        holder.setWindSpeed(currentWeather.wind.speed.toString() + " m/s")
    }

}