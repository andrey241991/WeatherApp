package com.example.home_pc.myrxexample.detailed.view

import com.arellomobile.mvp.MvpView
import com.example.home_pc.myrxexample.util.ProgressView

interface DetailedView : MvpView, ProgressView {
    fun getNetworkState()
    fun showNetworkError()
    fun showData()
}