package com.example.home_pc.myrxexample.detailed.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.home_pc.myrxexample.R
import com.example.home_pc.myrxexample.detailed.presenter.DetailedPresenter
import com.example.home_pc.myrxexample.detailed.view.adapter.CustomItemDecorator
import com.example.home_pc.myrxexample.detailed.view.adapter.WeatherAdapter
import com.example.home_pc.myrxexample.util.ConnectionHelper
import kotlinx.android.synthetic.main.activity_detailed.*

class DetailedActivity : MvpAppCompatActivity(), DetailedView {

    @InjectPresenter
    lateinit var presenter: DetailedPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
    }

    override fun getNetworkState() {
        var networkAccess = ConnectionHelper.hasConnection(this@DetailedActivity)
        presenter.networkAccess = networkAccess
    }

    override fun showNetworkError() {
        Toast.makeText(this@DetailedActivity, "Check Network Connection please", Toast.LENGTH_LONG).show()
    }

    override fun showData() {
        var weatherAdapter = WeatherAdapter()
        weatherAdapter.setPresenter(presenter)
        recycler.layoutManager = LinearLayoutManager(this@DetailedActivity, LinearLayoutManager.HORIZONTAL, false)
        var customDevider = CustomItemDecorator()
        recycler.addItemDecoration(customDevider)
        recycler.adapter = weatherAdapter
    }

    override fun showProgress() {
        progress_bar.visibility = VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = GONE
    }

}
