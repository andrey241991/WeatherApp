package com.example.home_pc.myrxexample.detailed.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.home_pc.myrxexample.R
import com.example.home_pc.myrxexample.detailed.presenter.DetailedPresenter

lateinit var presenter: DetailedPresenter

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        var view = LayoutInflater.from(viewGroup.context).inflate(R.layout.weather_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return presenter.getWetherListSize()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.onBindRepositoryRowViewAtPosition(position, holder);
    }

    fun setPresenter(detailedPresenter: DetailedPresenter) {
        presenter = detailedPresenter
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), WeatherRepositoryRowView {

        var txtDate : TextView = itemView.findViewById(R.id.txt_date)
        var txtTemperature : TextView = itemView.findViewById(R.id.txt_temp)
        var txtWindSpeed : TextView = itemView.findViewById(R.id.txt_wind_speed)
        var txtTime : TextView = itemView.findViewById(R.id.txt_time)

        override fun setDate(date: String) {
            txtDate.text = date
        }

        override fun setTime(time: String) {
            txtTime.text = time
        }


        override fun setTemperature(temp: String) {
            txtTemperature.text = temp
        }

        override fun setWindSpeed(windSpeed: String) {
            txtWindSpeed.text = windSpeed
        }

    }

}