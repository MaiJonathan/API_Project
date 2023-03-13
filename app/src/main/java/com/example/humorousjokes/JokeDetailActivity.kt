package com.example.humorousjokes

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
import java.lang.reflect.Type


class JokeDetailActivity : AppCompatActivity() {
    companion object{
        val EXTRA_JOKE = "JOKE"
    }

    private lateinit var binding: ActivityJokeDetailBinding
    lateinit var adapter: JokeAdapter
    lateinit var jokeList: ArrayList<Jokes>
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
        jokeList = arrayListOf(classicJoke)


//        val jokeId = intent.getStringExtra(JokeCreationActivity.EXTRA_ID) ?: ""

        val jokeText = intent.getStringExtra(JokeCreationActivity.EXTRA_JOKETEXT) ?: ""
        val jokeDelivery = intent.getStringExtra(JokeCreationActivity.EXTRA_JOKEDELIVERY) ?: ""
        val jokeSetup = intent.getStringExtra(JokeCreationActivity.EXTRA_JOKESETUP) ?: ""
        val jokeCategory = intent.getStringExtra(JokeCreationActivity.EXTRA_CATEGORY) ?: ""
        var jokeSaved = intent.getBooleanExtra(JokeCreationActivity.EXTRA_SAVED, false)
        var jokeRating = intent.getFloatExtra(JokeCreationActivity.EXTRA_RATING, 0F)

//        val jokeId = intent.getStringExtra(JokeCreationActivity.EXTRA_ID) ?: ""

        if (!jokeText.isEmpty()) {
            binding.textViewJokeDetailJoke.text = jokeText
        }
        if (!jokeDelivery.isEmpty()) {
            binding.textViewJokeDetailJoke.setText(jokeSetup)
            binding.textViewJokeDetailDelivery.setText(jokeDelivery)
        }
        binding.textViewJokeDetailCategory.text = jokeCategory

        //saves the joke to savedjokes.json
        binding.buttonJokeDetailJokeSaver.setOnCheckedChangeListener { buttonView, isChecked ->
            jokeSaved = !jokeSaved
            jokeRating = binding.ratingBarJokeDetail.rating
            System.out.println(joke)
            jokeList.add(joke)
            System.out.println(jokeList)
        }
        binding.buttonJokeDetailViewSavedJokes.setOnClickListener {

        }

    }

    //does not work probably
//    private fun createJsonData(){
//        var json = JSONObject()
//
//        json.put("frogs",classicJoke)
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
//        val fileName = "myJson"
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
//
//    private fun addJoke(theFunnyJoke: Jokes):JSONArray?{
//        var jokeJson = JSONArray()
//
//            jokeJson.put{
//                theFunnyJoke
//        }
//        return jokeJson
//    }
}

