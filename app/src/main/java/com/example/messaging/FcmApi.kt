package com.example.messaging

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET

import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface FcmApi {

    @POST("send")

    fun send(@HeaderMap header: HashMap<String,String>, @Body data: ReqModel): Call<ResModel>

    companion object Factory{

        fun createRetrofit(): FcmApi{

            val retrofit= Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://fcm.googleapis.com/fcm/")
                .build()

            return retrofit.create(FcmApi::class.java)
        }
    }
}