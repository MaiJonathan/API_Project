package com.example.humorousjokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.humorousjokes.databinding.ActivitySavedJokesBinding

class SavedJokes : AppCompatActivity() {
    private lateinit var binding: ActivitySavedJokesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedJokesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}