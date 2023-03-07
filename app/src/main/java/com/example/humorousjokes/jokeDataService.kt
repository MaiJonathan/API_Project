package com.example.humorousjokes

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface jokeDataService {
    @GET("joke/Any?")
    fun getJokeByType(
        @Query("blacklistFlags") blacklistFlags: List<String>,
        @Query("type") type:String,
        @Query("amount") amount: Int,
        @Query("safe") safe: Boolean,
    ): Call<Jokes>

}