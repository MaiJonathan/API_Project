package com.example.humorousjokes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Jokes(
    val category: String,
    val id: Int,
    val joke: String?,
    val lang: String,
    val safe: Boolean,
    val setup: String?,
    val delivery: String?,
    val type: String  = "none",
    var rating: Float = 0F,
    var saved: Boolean = false
) : Parcelable