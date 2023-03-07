package com.example.covidtracker

import com.example.humorousjokes.jokeDataService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitHelper {

    //always ends in a slash
    val baseUrl = "https://v2.jokeapi.dev/"

    fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
