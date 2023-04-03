package com.example.humorousjokes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.humorousjokes.JokeCreationActivity.Companion.EXTRA_JOKETHINGY
import com.example.humorousjokes.JokeCreationActivity.Companion.EXTRA_SAVEDJOKETHINGY
import com.example.humorousjokes.databinding.ActivityJokeDetailBinding

class JokeDetailActivity : AppCompatActivity() {
    companion object{
        val SAVEDJOKES = "PLEASE"
        val EXTRA_JOKE = "WORK"
    }

    private lateinit var binding: ActivityJokeDetailBinding
    private lateinit var joke: Jokes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var jokeList = ArrayList<Jokes>()
        val creationJoke = intent.getParcelableExtra<Jokes>(EXTRA_JOKETHINGY)
        val savedJoke = intent.getParcelableExtra<Jokes>(EXTRA_JOKE)
        val test = intent.getParcelableArrayListExtra<Jokes>(EXTRA_SAVEDJOKETHINGY)

        if (test != null) {
            jokeList = test}
        if(creationJoke != null){
            joke = creationJoke
            joke.saved = false
        }
        if(savedJoke != null){
            joke = savedJoke
            binding.switchJokeDetailJokeSaver.isChecked = true
            binding.ratingBarJokeDetail.rating = joke.rating
            binding.ratingBarJokeDetail.isClickable = false
            joke.saved = true
        }
        if (joke.joke?.isNotEmpty() == true) {
            binding.textViewJokeDetailJoke.text = joke.joke
        }
        if (joke.delivery?.isNotEmpty() == true) {
            binding.textViewJokeDetailJoke.setText(joke.setup)
            binding.textViewJokeDetailDelivery.setText(joke.delivery)
        }
        binding.textViewJokeDetailCategory.text = joke.category


        binding.switchJokeDetailJokeSaver.setOnCheckedChangeListener { buttonView, isChecked ->
            if(!joke.saved)
            {
                joke.saved = true
                binding.ratingBarJokeDetail.isClickable = true
                joke.rating = binding.ratingBarJokeDetail.rating
                jokeList.add(joke)
            }
            else if(joke.saved)
            {
                jokeList.remove(joke)
                binding.ratingBarJokeDetail.isClickable = false
                joke.rating = 0F
                binding.ratingBarJokeDetail.rating = joke.rating
            }

        }

        binding.buttonJokeDetailViewSavedJokes.setOnClickListener {
            val savedJokeIntent = Intent(it.context, SavedJokes::class.java)
            savedJokeIntent.putExtra(SAVEDJOKES, jokeList)
            it.context.startActivity(savedJokeIntent)
        }

        binding.buttonJokedDetailCreationButton.setOnClickListener {
            val savedJokeIntent = Intent(it.context, JokeCreationActivity::class.java)
            savedJokeIntent.putExtra(EXTRA_SAVEDJOKETHINGY, jokeList)
            it.context.startActivity(savedJokeIntent)
        }

    }
}

