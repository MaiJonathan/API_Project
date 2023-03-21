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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedJokesBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val path = this.filesDir.absolutePath
//        val file = File("$path/LET/savedjokes.json")
//        getSavedJokesFromFileThingy(file)
        //use serialization
        var savedJokes = intent.getParcelableExtra<Jokes>(JokeDetailActivity.SAVEDJOKES)
        System.out.println(savedJokes)
    }


    private fun getSavedJokesFromFileThingy(file: File){
        val gson = Gson()
        val jsonString = file.bufferedReader().use {
            it.readText()
        }
        val type = object : TypeToken<List<Jokes>>() { }.type
        val savedJokesList = gson.fromJson<List<Jokes>>(jsonString, type)

        adapter = JokeAdapter(savedJokesList)
        binding.RecyclerViewSavedJokes.adapter = adapter
        binding.RecyclerViewSavedJokes.layoutManager = LinearLayoutManager(null)
    }
}