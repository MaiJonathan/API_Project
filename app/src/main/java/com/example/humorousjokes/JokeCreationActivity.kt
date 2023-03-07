package com.example.humorousjokes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.covidtracker.RetrofitHelper
import com.example.humorousjokes.databinding.ActivityJokeCreationBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokeCreationActivity : AppCompatActivity() {
    companion object {
        const val TAG = "JokeListActivity"
        val EXTRA_JOKETEXT = "jokehaha"
        val EXTRA_JOKEDELIVERY = "jokehehe"
        val EXTRA_CATEGORY = "category"
        val EXTRA_ID = "5"
    }
    val startRegistrationForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
        }
    }

    private lateinit var binding: ActivityJokeCreationBinding
    private lateinit var joke: Jokes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeCreationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val FLAGS = listOf("nsfw", "religious", "politcal", "racist", "sexist", "explicit")

        fun getTheHumorousJokes(type: String, amount: Int, safe: Boolean) {
            val jokeDataService = RetrofitHelper.getInstance().create(jokeDataService::class.java)
            val jokeDataCall = jokeDataService.getJokeByType(FLAGS, type, amount, safe)

            jokeDataCall.enqueue(object : Callback<Jokes> {
                override fun onResponse(
                    call: Call<Jokes>,
                    response: Response<Jokes>
                ) {
                    Log.d(TAG, "onRun: ${response.body()}")
                    //the three lines to create the CountyAdapter
                    if (response.body() != null) {
                        System.out.println(response.raw())
                        joke = response.body()!!
                    } else {
                        Log.d(TAG, "null")
                    }
                    //the response.body() is the List<CountyData>

                }

                override fun onFailure(call: Call<Jokes>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }
            })
        }
        getTheHumorousJokes("Any",1, true)

        binding.buttonMainJokeCreator.setOnClickListener {
            // 1. Create an intent object with the current activity
            //and the destination activity's class
            val registrationIntent = Intent(this, JokeDetailActivity::class.java)
            //2 optionally add information to send with the intent
            //key-value pai rs just like with Bundles
            registrationIntent.putExtra(EXTRA_JOKETEXT, joke.joke)
//            registrationIntent.putExtra(EXTRA_JOKEDELIVERY, joke.delivery)
            registrationIntent.putExtra(EXTRA_CATEGORY, joke.category)
            registrationIntent.putExtra(EXTRA_ID, joke.id)
//            //3a. launch the new activity using the intent
//            startActivity(registrationIntent)
            //3b. Launch the activity for a result using the variable from the register for result contract above
            startRegistrationForResult.launch(registrationIntent)
        }
    }
}