package com.example.movieroom

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

//Binding adapter to take the url of an image and load it into the imageview with Glide
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl : String){
    //only execute this code if imageUrl is not null
    imgUrl.let {
        //convert imageUrl to a Uri
        val imgUri = it.toUri().buildUpon().scheme("https").build()

        //load the image with Glide
        Glide.with(imageView.context)
                .load(imgUri)
                .apply(RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(imageView)
    }
}

//Binding adapter for initializing our recyclerview adapter with list data
//using binding adapter to set the recyclerview data will cause data binding to observe the live data the holds the list of movies
//then this adapter wwill be called automatically whenever the movie list changes
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView : RecyclerView, data : List<Movie>?) {
    val adapter = recyclerView.adapter as DisplayMoviesAdapter
    adapter.submitList(data)
}

//Binding adapter that updates what's shown on the screens that fetch and display data based on the MovieApiStatus value
@BindingAdapter("movieApiStatus")
fun bindStatus(statusImageView : ImageView, status : MovieApiStatus?) {
    when(status) {
        MovieApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        MovieApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }

        MovieApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

//Binding adapter that coverts the genre ids to text that will be displayed
@BindingAdapter("movieGenres")
fun bindGenre(genreTextView : TextView, genresList : List<Int>?) {
    //final string for the genreTextView
    var genreString = ""

    //if the genreList is not null, build the string
    if(genresList != null){

        //loop through the genreList of ids and add the correct text value to the final string
        for(i in 0..genresList.lastIndex) {

            //if it is not the first element, add a comma and space to the final string
            if(i != 0){
                genreString += ", "
            }

            //Add the genre text to the final string(genreString) when a match for the id is found
            genreString += when(genresList[i]) {
                28 -> "Action"
                12 -> "Adventure"
                16 -> "Animation"
                35 -> "Comedy"
                80 -> "Crime"
                18 -> "Drama"
                10751 -> "Family"
                14 -> "Fantasy"
                27 -> "Horror"
                9648 -> "Mystery"
                10749 -> "Romance"
                878 -> "Science Fiction"
                53 -> "Thriller"
                else -> ""
            }
        }
    }

    //set the textview to display the final string we just built
    genreTextView.text = genreString
}