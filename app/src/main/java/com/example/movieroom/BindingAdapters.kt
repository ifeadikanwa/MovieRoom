package com.example.movieroom

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

//Binding adapter to take the url of an image and load it into the imageview with Glide
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl : String){
    //only execute this code if imageUrl is not null
    imgUrl?.let {
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