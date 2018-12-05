package com.example.home_pc.myrxexample.main.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.home_pc.myrxexample.api.ApiClient
import com.example.home_pc.myrxexample.api.ApiClient.Companion.APP_ID
import com.example.home_pc.myrxexample.api.WeatherData
import com.example.home_pc.myrxexample.api.WeatherList
import com.example.home_pc.myrxexample.main.view.MainActivity.Companion.AFTER_TOMORROW
import com.example.home_pc.myrxexample.main.view.MainActivity.Companion.TODAY
import com.example.home_pc.myrxexample.main.view.MainActivity.Companion.TOMORROW
import com.example.home_pc.myrxexample.main.view.MainView
import com.example.home_pc.myrxexample.main.view.WeatherRowView
import com.example.home_pc.myrxexample.model.ParseJSONHelper
import com.example.home_pc.myrxexample.util.StringDateHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    var networkAccess: Boolean = false
    var celsiusMetric: Boolean = true
    lateinit var weatherList : ArrayList<WeatherList>
    var celsiusMetricString = "metric"

    companion object {
        const val CELSIUS = "metric"
        const val FAHRENHEITS = "imperial"
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getDateFromNetwork()
    }

    fun getDateFromNetwork() {
        viewState.getNetworkState()
        viewState.showProgress()
        viewState.getMetric()
        when{
            celsiusMetric -> celsiusMetricString = CELSIUS
            !celsiusMetric -> celsiusMetricString = FAHRENHEITS
        }

        if (!networkAccess) {
            viewState.showNetworkError()
            return
        }

        val api = ApiClient()
        val client = api.getClient()
        val call = client.getWeatherDate("Cherkasy", "json", celsiusMetricString, APP_ID)
        call.enqueue(object : Callback<WeatherData> {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                val weatherData = response.body()
                weatherList = ParseJSONHelper.getActualWeatherList(weatherData!!.list)
                viewState.hideProgress()
                viewState.setDate(weatherList)
                viewState.setTemperature(weatherList[0])
                setWeatherSign()
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                Log.v("TAG", "error =" + t.message);
                viewState.hideProgress()
            }

        })
    }

    private fun setWeatherSign() {
        when(celsiusMetric){
            true -> viewState.showCelciusSign()
            false -> viewState.showFahrenheitsSign()
        }
    }

    fun getStringDate(stringConst: String): String {
        return StringDateHelper.getDate(stringConst)
    }

    fun getTitle(date: String, const: String): CharSequence? {
        when {
            const.equals(TODAY) -> return "Today"
            const.equals(TOMORROW) -> return "Tomorow"
            const.equals(AFTER_TOMORROW) -> return date
        }
        return ""
    }

    fun addDateToContainerLayout(weatherRowView: WeatherRowView, date: String){
        for (i in 0..weatherList.size - 1) {
            var weatherList = weatherList.get(i)
            if (weatherList.dt_txt.equals(date + " ${ParseJSONHelper.morning}")) {
                weatherRowView.setMorning(weatherList.main.temp.toInt().toString())
            }

            if (weatherList.dt_txt.equals(date + " ${ParseJSONHelper.day}")) {
                weatherRowView.setDay(weatherList.main.temp.toInt().toString())
            }

            if (weatherList.dt_txt.equals(date + " ${ParseJSONHelper.evening}")) {
                weatherRowView.setEvening(weatherList.main.temp.toInt().toString())
            }
        }
    }

}
