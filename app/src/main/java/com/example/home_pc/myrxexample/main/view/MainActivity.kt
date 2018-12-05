package com.example.home_pc.myrxexample.main.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.home_pc.myrxexample.R
import com.example.home_pc.myrxexample.api.WeatherList
import com.example.home_pc.myrxexample.main.presenter.MainPresenter
import java.util.*
import android.widget.Toast
import com.example.home_pc.myrxexample.router.Router
import com.example.home_pc.myrxexample.settings.SettingsActivity
import com.example.home_pc.myrxexample.util.ConnectionHelper
import com.example.home_pc.myrxexample.util.SharedPrefs
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : MvpAppCompatActivity(), MainView, WeatherRowView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    companion object {
        const val METRIC_RESULT = 100
        const val TODAY = "TODAY"
        const val TOMORROW = "TOMORROW"
        const val AFTER_TOMORROW = "AFTER_TOMORROW"
        const val BOTTOM_MARGIN = 10
    }

    lateinit var weatherList: ArrayList<WeatherList>
    lateinit var container: LinearLayout
    lateinit var txtMorning: TextView
    lateinit var txtDay: TextView
    lateinit var txtEvening: TextView
    lateinit var txtDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        container = findViewById(R.id.container)
        container.setOnClickListener { Router.startDetailedScreen(this) }

        swipeRefreshLayout.setOnRefreshListener {
            presenter.getDateFromNetwork()
            if (swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.change_metric -> {
                changeMetric()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun changeMetric() {
        startActivityForResult(Intent(this@MainActivity, SettingsActivity::class.java), METRIC_RESULT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            Toast.makeText(this@MainActivity, "Settings were changed successfully", Toast.LENGTH_LONG).show()
            getMetric()
            presenter.getDateFromNetwork()
        } else {
            Toast.makeText(this@MainActivity, "Settings were not changed", Toast.LENGTH_LONG).show()
        }
    }

    override fun showNetworkError() {
        Toast.makeText(this@MainActivity, "Check Network Connection please", Toast.LENGTH_LONG).show()
    }

    override fun setDate(weatherList: ArrayList<WeatherList>) {
        this.weatherList = weatherList
        container.removeAllViews()
        addLayout(presenter.getStringDate(TODAY), TODAY)
        addLayout(presenter.getStringDate(TOMORROW), TOMORROW)
        addLayout(presenter.getStringDate(AFTER_TOMORROW), AFTER_TOMORROW)
    }

    fun addLayout(date: String, const: String) {
        val view = LayoutInflater.from(this@MainActivity).inflate(R.layout.wether_content_layout, null, true)
        txtMorning = view.findViewById(R.id.txt_morning)
        txtDay = view.findViewById(R.id.txt_day)
        txtEvening = view.findViewById(R.id.txt_evening)
        txtDate = view.findViewById(R.id.txt_date)
        txtDate.text = presenter.getTitle(date, const)
        presenter.addDateToContainerLayout(this, date)
        container.addView(view, getParams())
    }

    override fun setMorning(temp: String) {
        txtMorning.text = temp
    }

    override fun setDay(temp: String) {
        txtDay.text = temp
    }

    override fun setEvening(temp: String) {
        txtEvening.text = temp
    }

    fun getParams(): LinearLayout.LayoutParams {
        var params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.topMargin = BOTTOM_MARGIN
        return params
    }

    override fun setTemperature(weatherList: WeatherList) {
        var textView = findViewById<TextView>(R.id.txtBigTemperature)
        textView.text = weatherList.main.temp.toInt().toString()
    }

    override fun getNetworkState() {
        var networkAccess = ConnectionHelper.hasConnection(this@MainActivity)
        presenter.networkAccess = networkAccess
    }

    override fun getMetric() {
        var celsius = SharedPrefs.getMetric(this@MainActivity)
        presenter.celsiusMetric = celsius
    }

    override fun showCelciusSign() {
        metric_sign.text = "C"
    }

    override fun showFahrenheitsSign() {
        metric_sign.text = "F"
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

}
