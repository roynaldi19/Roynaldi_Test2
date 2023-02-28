package com.roynaldi19.roynaldi_test2.api

import com.roynaldi19.roynaldi_test2.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("forecast?id=1642907&appid=b50d2b12283d00ccf2ee1a10798d41f4")
    //1642907 id jakarta
    fun getWeather(
    ): Call<WeatherResponse>
}