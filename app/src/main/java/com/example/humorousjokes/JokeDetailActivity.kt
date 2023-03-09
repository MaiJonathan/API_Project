package com.example.humorousjokes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
        val jokeDelivery = intent.getStringExtra(JokeCreationActivity.EXTRA_JOKEDELIVERY) ?: ""
        val jokeSetup = intent.getStringExtra(JokeCreationActivity.EXTRA_JOKESETUP)?: ""
        val jokeCategory = intent.getStringExtra(JokeCreationActivity.EXTRA_CATEGORY)?:""
        var jokeSaved = intent.getBooleanExtra(JokeCreationActivity.EXTRA_SAVED,false)
        var jokeRating = intent.getFloatExtra(JokeCreationActivity.EXTRA_RATING,0F)

//        val jokeId = intent.getStringExtra(JokeCreationActivity.EXTRA_ID) ?: ""

        if(!jokeText.isEmpty())
        {
            binding.textViewJokeDetailJoke.text = jokeText
        }
        if(!jokeDelivery.isEmpty())
        {
            binding.textViewJokeDetailJoke.setText(jokeSetup)
            binding.textViewJokeDetailDelivery.setText(jokeDelivery)
        }
        binding.textViewJokeDetailCategory.text = jokeCategory

        //saves the joke to savedjokes.json
        binding.buttonJokeDetailJokeSaver.setOnCheckedChangeListener { buttonView, isChecked ->
            jokeSaved = !jokeSaved
            jokeRating = binding.ratingBarJokeDetail.rating



        }
    }
}

