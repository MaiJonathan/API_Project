package com.example.humorousjokes

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
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
        val EXTRA_JOKETHINGY = "jokething"
        val EXTRA_SAVEDJOKETHINGY = "jokeThigny"
    }
    val startRegistrationForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
    }

    private lateinit var binding: ActivityJokeCreationBinding
    private lateinit var joke: Jokes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeCreationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val FLAGS = listOf("nsfw", "religious", "politcal", "racist", "sexist", "explicit")
        var typeSingleChecked = false
        var typeDoubleChecked = false
        var jokeCreated = false
        val savedJokes = intent.getParcelableArrayListExtra<Jokes>(EXTRA_SAVEDJOKETHINGY)

        fun getTheHumorousJokes(type: String, amount: Int, safe: Boolean) {
            val jokeDataService = RetrofitHelper.getInstance().create(jokeDataService::class.java)
            val jokeDataCall = jokeDataService.getJokeByType(FLAGS, type, amount, safe)

            jokeDataCall.enqueue(object : Callback<Jokes> {
                override fun onResponse(
                    call: Call<Jokes>,
                    response: Response<Jokes>
                ) {
                    Log.d(TAG, "onRun: ${response.body()}")
                    if (response.body() != null) {
                        System.out.println(response.raw())
                        joke = response.body()!!
                    } else {
                        Log.d(TAG, "null")
                    }
                    jokeCreated = true
                }

                override fun onFailure(call: Call<Jokes>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }
            })
        }

        binding.buttonMainJokeViewer.setOnClickListener {
            if(jokeCreated) {
                val registrationIntent = Intent(this, JokeDetailActivity::class.java)
                registrationIntent.putExtra(EXTRA_JOKETHINGY, joke)
                registrationIntent.putExtra(EXTRA_SAVEDJOKETHINGY, savedJokes)
                startRegistrationForResult.launch(registrationIntent)
            }
            else
            {
                Toast.makeText(this, "You must create the joke first or try again", Toast.LENGTH_SHORT).show()
            }
        }
        binding.switchMainJokeTypeSingle.setOnCheckedChangeListener { buttonView, isChecked ->
            typeSingleChecked = !typeSingleChecked
        }
        binding.switchMainJokeTypeDouble.setOnCheckedChangeListener { buttonView, isChecked ->
            typeDoubleChecked = !typeDoubleChecked
        }
        binding.buttonMainJokeCreator.setOnClickListener {
            var type = ""
            if(typeDoubleChecked == typeSingleChecked){type = "any" }
            else if(typeDoubleChecked){type = "double"}
            else if(typeSingleChecked){type = "single"}
            getTheHumorousJokes(type, 1, true)
        }
    }
    override fun onCreateOptionsMenu (menu : Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.joke_info_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.Item_jokeInfo -> {
                val builder = AlertDialog.Builder(this)
                with(builder){
                    setTitle("Info")
                    setMessage("You must create the joke before viewing it\n\nSingle: Creates a one-liner joke\nDouble: Creates a joke with a setup and punchline\nNone or Both: Creates a joke with either type")
                    show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}