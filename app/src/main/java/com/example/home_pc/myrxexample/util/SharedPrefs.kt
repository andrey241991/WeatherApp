package com.example.home_pc.myrxexample.util

import android.content.Context
import android.content.SharedPreferences


class SharedPrefs {

    companion object {
        const val APP_PREFERENCES = "APP_PREFERENCES"
        const val METRIC_KEY = "METRIC_KEY"

        fun setMetric(context: Context, celsius: Boolean) {
            var editor: SharedPreferences.Editor = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).edit()
            editor.putBoolean(METRIC_KEY, celsius)
            editor.apply()
        }

        fun getMetric(context: Context): Boolean {
            var shared: SharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
            return shared.getBoolean(METRIC_KEY, false)
        }
    }
}