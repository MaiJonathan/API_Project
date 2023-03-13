package com.example.humorousjokes

import android.os.Parcelable
import android.widget.RatingBar
import kotlinx.parcelize.Parcelize

@Parcelize
data class JokeData(
    val error: Boolean,
    val amount: Int,
    val jokes: List<Jokes>,
) :  Parcelable

@Parcelize
data class Jokes(
    val category: String,
    val id: Int,
    val joke: String?,
    val lang: String,
    val safe: Boolean,
    val setup: String?,
    val delivery: String?,
    val type: String,
    var rating: Float = 0F,
    var saved: Boolean = false
) : Parcelable