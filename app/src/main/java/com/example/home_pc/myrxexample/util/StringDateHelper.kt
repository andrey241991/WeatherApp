package com.example.home_pc.myrxexample.util

import com.example.home_pc.myrxexample.main.view.MainActivity.Companion.AFTER_TOMORROW
import com.example.home_pc.myrxexample.main.view.MainActivity.Companion.TODAY
import com.example.home_pc.myrxexample.main.view.MainActivity.Companion.TOMORROW
import java.text.SimpleDateFormat
import java.util.*

class StringDateHelper {

    companion object {
        fun getDate(constName: String): String {
            val calendar = Calendar.getInstance()
            when (constName) {
                TODAY -> return SimpleDateFormat("yyyy-MM-dd").format(calendar.time)

                TOMORROW -> {
                    calendar.add(Calendar.DAY_OF_YEAR, 1)
                    return SimpleDateFormat("yyyy-MM-dd").format(calendar.time)
                }
                AFTER_TOMORROW -> {
                    calendar.add(Calendar.DAY_OF_YEAR, 2)
                    return SimpleDateFormat("yyyy-MM-dd").format(calendar.time)
                }

            }
            return ""
        }
    }
}
