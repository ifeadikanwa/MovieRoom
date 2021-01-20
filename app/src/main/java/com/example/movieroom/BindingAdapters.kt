package com.example.movieroom

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
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
//            .apply(RequestOptions()
//                .placeholder(R.drawable.ic_loading_animation)
//                .error(R.drawable.ic_broken_image))
            .into(imageView)
    }
}