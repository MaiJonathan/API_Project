package com.example.humorousjokes

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import com.example.humorousjokes.JokeCreationActivity.Companion.EXTRA_JOKETHINGY
import com.example.humorousjokes.databinding.ActivityJokeDetailBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.Writer


class JokeDetailActivity : AppCompatActivity() {
    companion object{
        val SAVEDJOKES = "WORK"
        val EXTRA_JOKE = "JOKE"
    }

    private lateinit var binding: ActivityJokeDetailBinding
    var classicJoke = Jokes(
        category = "Misc",
        joke = "A perfectionist walked into a bar... apparently, the bar was not set high enough.",
        delivery = "",
        id = 288 ,
        setup = "",
        lang = "",
        safe = true,
        type = "",

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var joke = intent.getParcelableExtra<Jokes>(EXTRA_JOKETHINGY)?: classicJoke
        var jokeList = ArrayList<Jokes>()

//        val jokeText = intent.getStringExtra(JokeCreationActivity.EXTRA_JOKETEXT) ?: ""
//        val jokeDelivery = intent.getStringExtra(JokeCreationActivity.EXTRA_JOKEDELIVERY) ?: ""
//        val jokeSetup = intent.getStringExtra(JokeCreationActivity.EXTRA_JOKESETUP) ?: ""
//        val jokeCategory = intent.getStringExtra(JokeCreationActivity.EXTRA_CATEGORY) ?: ""
//        var jokeSaved = intent.getBooleanExtra(JokeCreationActivity.EXTRA_SAVED, false)
//        var jokeRating = intent.getFloatExtra(JokeCreationActivity.EXTRA_RATING, 0F)
//        val jokeId = intent.getStringExtra(JokeCreationActivity.EXTRA_ID) ?: ""

        if (joke.joke?.isNotEmpty() == true) {
            binding.textViewJokeDetailJoke.text = joke.joke
        }
        if (joke.delivery?.isNotEmpty() == true) {
            binding.textViewJokeDetailJoke.setText(joke.setup)
            binding.textViewJokeDetailDelivery.setText(joke.delivery)
        }
        binding.textViewJokeDetailCategory.text = joke.category

        //saves the joke to savedjokes.json
        binding.buttonJokeDetailJokeSaver.setOnCheckedChangeListener { buttonView, isChecked ->
            joke.saved = !joke.saved
            joke.rating = binding.ratingBarJokeDetail.rating
            System.out.println(joke)
            jokeList.add(joke)
//
//            val gson = Gson()
//            var savedJokesFile = createFile()
//            createJsonData()
//            val path = this.filesDir.absolutePath
//            val file = File("$path/LET/savedjokes.json")
//            System.out.println(file)
//
//            val jsonTutsList: String = gson.toJson(jokeList)
//            savedJokesFile.writeText(jsonTutsList)
//            val jsonString = savedJokesFile.bufferedReader().use {
//
//                it.readText()
//            }
//            val type = object : TypeToken<List<Jokes>>() { }.type
//            val questions = gson.fromJson<List<Jokes>>(jsonString, type)
//            System.out.println(questions + "linus")


        }
        binding.buttonJokeDetailViewSavedJokes.setOnClickListener {
            val savedJokeIntent = Intent(it.context, SavedJokes::class.java)
            savedJokeIntent.putExtra(SAVEDJOKES, jokeList)
            it.context.startActivity(savedJokeIntent)

        }

    }

//    private fun createJsonData(){
//        var json = JSONObject()
//
//        json.put("savedjokes",classicJoke)
//        saveJson(json.toString())
//
//    }
//    private fun saveJson(s:String){
//        val output: Writer
//        val path = this.getExternalFilesDir(null)
//        val letDirectory = File(path, "LET")
//        letDirectory.mkdirs()
//
//        var file = File(letDirectory, "savedjokes.json")
//        if(!file.exists()){
//            file.createNewFile()
//        }
//
//        output = BufferedWriter(FileWriter(file))
//        output.write(s)
//        output.close()
//        System.out.println(file)
//    }
//
//    private fun createFile(): File{
//        val fileName = "savedjokes"
//        val storageDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
//        if (storageDir != null) {
//            if(!storageDir.exists()){
//                storageDir.mkdir()
//            }
//        }
//        return File.createTempFile(
//            fileName,
//            ".json",
//            storageDir
//        )
//    }
//    private fun addJoke(theFunnyJoke: Jokes):JSONArray?{
//        var jokeJson = JSONArray()
//
//        jokeJson.put{
//            theFunnyJoke
//        }
//        return jokeJson
//    }
}

