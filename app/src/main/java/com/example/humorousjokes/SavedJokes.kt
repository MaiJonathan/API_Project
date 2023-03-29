package com.example.humorousjokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.humorousjokes.databinding.ActivitySavedJokesBinding

class SavedJokes : AppCompatActivity() {
    private lateinit var binding: ActivitySavedJokesBinding
    lateinit var adapter: JokeAdapter
    lateinit var savedJokesList: List<Jokes>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedJokesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var test = intent.getParcelableArrayListExtra<Jokes>(JokeDetailActivity.SAVEDJOKES)
        if (test != null) {
            savedJokesList = test.toList()}
        Log.d("hi","$savedJokesList")
        getSavedJokes()
    }
    override fun onCreateOptionsMenu (menu : Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.saved_list_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.Item_savedLIstMenu_sortByRating -> {
                savedJokesList = savedJokesList.sortedBy {
                    it.rating
                }
                getSavedJokes()
                true
            }
            R.id.Item_savedListMenu_sortByCategory -> {
                savedJokesList = savedJokesList.sortedBy {
                    it.category
                }
                getSavedJokes()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun getSavedJokes(){
        adapter = JokeAdapter(savedJokesList)
        binding.RecyclerViewSavedJokes.adapter = adapter
        binding.RecyclerViewSavedJokes.layoutManager = LinearLayoutManager(null)
    }
}