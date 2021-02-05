package com.example.movieroom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieroom.databinding.DisplayMoviesListItemBinding

class DisplayMoviesAdapter(private val onClickListener : OnClickListener) :ListAdapter<Movie, DisplayMoviesAdapter.MovieViewHolder>(DiffCallback) {

    //companion object so that a reference to the class isn't needed to access it
    companion object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            //using kotlin's referential equality operator "===" to check if the object references are the same
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            //we use the movie id to check if the contents of the object are the same
            return oldItem.id == newItem.id
        }

    }

    //we're going to pass in our list item binding variable so we can bind the movie properties to the layout
    class MovieViewHolder(private var binding: DisplayMoviesListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie : Movie) {
            binding.movie = movie
            //this causes the property update to execute immediately
            binding.executePendingBindings()
        }

    }

    //create a view holder by inflating the list item binding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayMoviesAdapter.MovieViewHolder {
        return MovieViewHolder(DisplayMoviesListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    //we get the item and then pass it to the bind method in our view holder
    override fun onBindViewHolder(holder: DisplayMoviesAdapter.MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)

        //setup onClickListener and pass the movie on button click
        holder.itemView.setOnClickListener{
            onClickListener.onClick(movie)
        }
    }

    // create an internal OnClickListener class with a lambda in its constructor
    // that initializes a matching onClick function
    class OnClickListener(val clickListener : (movie : Movie) -> Unit) {
        fun onClick(movie : Movie) = clickListener(movie)
    }

}