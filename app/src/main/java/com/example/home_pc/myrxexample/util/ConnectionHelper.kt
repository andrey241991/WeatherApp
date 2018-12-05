package com.example.home_pc.myrxexample.util

import android.content.Context
import android.net.ConnectivityManager

class ConnectionHelper {

    companion object {
        fun hasConnection(context:Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }
    }

}