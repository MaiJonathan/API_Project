package com.example.humorousjokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.humorousjokes.databinding.ActivitySavedJokesBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class SavedJokes : AppCompatActivity() {
    private lateinit var binding: ActivitySavedJokesBinding
    lateinit var adapter: JokeAdapter
    lateinit var savedJokesList: List<Jokes>
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
        binding = ActivitySavedJokesBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val path = this.filesDir.absolutePath
//        val file = File("$path/LET/savedjokes.json")
//        getSavedJokesFromFileThingy(file)
        var test = intent.getParcelableArrayListExtra<Jokes>(JokeDetailActivity.SAVEDJOKES)
        if(test.isNullOrEmpty()){
            if (test != null) {
                test.add(classicJoke)
            }
        }
        if (test != null) {
            savedJokesList = test.toList()
        }
        getSavedJokesFromFileThingy()
    }


    private fun getSavedJokesFromFileThingy(){
        adapter = JokeAdapter(savedJokesList)
        binding.RecyclerViewSavedJokes.adapter = adapter
        binding.RecyclerViewSavedJokes.layoutManager = LinearLayoutManager(null)
    }
}