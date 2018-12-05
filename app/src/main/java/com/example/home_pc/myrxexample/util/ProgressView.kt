package com.example.home_pc.myrxexample.util

import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface ProgressView {
    abstract fun showProgress()
    abstract fun hideProgress()
}