package com.example.humorousjokes

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class JokeAdapter (var dataSet: List<Jokes>):
    RecyclerView.Adapter<JokeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewJoke: TextView
        val layout: ConstraintLayout

        init {
            // Define click listener for the ViewHolder's View
            textViewJoke = view.findViewById(R.id.textView_jokeItem_jokePreview)
            layout = view.findViewById(R.id.layout_main)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_joke_data, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        var context = viewHolder.textViewJoke.context
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val jokeData = dataSet[position]
        viewHolder.textViewJoke.text = jokeData.joke

        viewHolder.layout.setOnClickListener {
            val detailIntent = Intent(it.context, JokeDetailActivity::class.java)
            //2 optionally add information to send with the intent
            //key-value pai rs just like with Bundles
            detailIntent.putExtra(JokeDetailActivity.EXTRA_JOKE, jokeData)
            //3a. launch the new activity using the intent
            //3b. Launch the activity for a result using the variable from the register for result contract above
            it.context.startActivity(detailIntent)
        }
    }


    override fun getItemCount() = dataSet.size
}
