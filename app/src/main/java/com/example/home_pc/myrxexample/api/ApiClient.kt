package com.example.home_pc.myrxexample.api

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor



class ApiClient {

    companion object {
        val APP_ID:String = "5fcd74844ca5019c2f5575501412649b"
        val BASE_URL:String = "http://api.openweathermap.org/"
    }

    private var retrofit: Retrofit? = null

    fun getClient(): Api {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val callAdapterFactory = RxJavaCallAdapterFactory.create()

        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(callAdapterFactory)
                .client(client)
                .build()

        return retrofit!!.create(Api::class.java)
    }

}