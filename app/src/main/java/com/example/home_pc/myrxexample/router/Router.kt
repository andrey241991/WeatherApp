package com.example.home_pc.myrxexample.router

import android.content.Context
import android.content.Intent
import com.example.home_pc.myrxexample.detailed.view.DetailedActivity

class Router {

    companion object {
        fun startDetailedScreen(context: Context){
            var intent = Intent(context, DetailedActivity::class.java)
            context.startActivity(intent)
        }
    }
}