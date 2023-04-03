package com.example.humorousjokes

import android.content.Intent
import android.view.*
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class JokeAdapter (var dataSet: List<Jokes>):
    RecyclerView.Adapter<JokeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewJoke: TextView
        val ratingbarJoke: RatingBar
        val layout: ConstraintLayout
        val category: TextView

        init {
            textViewJoke = view.findViewById(R.id.textView_jokeItem_Joke)
            ratingbarJoke = view.findViewById(R.id.ratingBar_jokeItem_JokeRating)
            layout = view.findViewById(R.id.ConstrainLayout_JokeItem)
            category = view.findViewById(R.id.textView_jokeItem_category)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_joke_data, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val jokeData = dataSet[position]
        if(jokeData.joke.isNullOrEmpty()) {
            viewHolder.textViewJoke.text = jokeData.setup
        }
        else{viewHolder.textViewJoke.text = jokeData.joke}
        viewHolder.ratingbarJoke.rating = jokeData.rating
        viewHolder.category.text = jokeData.category

        viewHolder.layout.setOnClickListener {
            val detailIntent = Intent(it.context, JokeDetailActivity::class.java)
            val arrayListHelper = ArrayList<Jokes>()
            dataSet.forEach {Jokes -> arrayListHelper.add(Jokes)}

            detailIntent.putExtra(JokeDetailActivity.EXTRA_JOKE, jokeData)
            detailIntent.putExtra(JokeCreationActivity.EXTRA_SAVEDJOKETHINGY,arrayListHelper)
            it.context.startActivity(detailIntent)
        }
    }

    override fun getItemCount() = dataSet.size
}
