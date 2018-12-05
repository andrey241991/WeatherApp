package com.example.home_pc.myrxexample.settings

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.home_pc.myrxexample.R
import kotlinx.android.synthetic.main.activity_settings.*
import android.widget.Toast
import com.example.home_pc.myrxexample.util.SharedPrefs


class SettingsActivity : AppCompatActivity() {


    var strMetric:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>,
                                        itemSelected: View, selectedItemPosition: Int, selectedId: Long) {
                val choose = resources.getStringArray(R.array.spinnerArray)
                strMetric = choose[selectedItemPosition]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        btnUpdate.setOnClickListener {
            if(strMetric == "Celsius"){
                SharedPrefs.setMetric(this@SettingsActivity, true)
            }else{
                SharedPrefs.setMetric(this@SettingsActivity, false)
            }
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
