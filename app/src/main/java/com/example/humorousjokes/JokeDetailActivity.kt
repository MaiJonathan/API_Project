package com.example.humorousjokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.humorousjokes.databinding.ActivityJokeDetailBinding

class JokeDetailActivity : AppCompatActivity() {

    companion object{
        val EXTRA_JOKE = "JOKE"
    }

    private lateinit var binding: ActivityJokeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jokeText = intent.getStringExtra(JokeCreationActivity.EXTRA_JOKETEXT) ?: ""
//        val jokeDelivery = intent.getStringExtra(JokeCreationActivity.EXTRA_JOKEDELIVERY) ?: ""
        val jokeCategory = intent.getStringExtra(JokeCreationActivity.EXTRA_CATEGORY) ?: ""
//        val jokeId = intent.getStringExtra(JokeCreationActivity.EXTRA_ID) ?: ""

        binding.textViewJokeDetailJoke.setText(jokeText)
//        binding.textViewJokeDetailJoke.setText(jokeDelivery)
        binding.textViewJokeDetailJoke.setText(jokeCategory)
//        binding.textViewJokeDetailJoke.setText(jokeId)
    }
}