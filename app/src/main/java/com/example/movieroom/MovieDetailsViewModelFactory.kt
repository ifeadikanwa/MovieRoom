package com.example.movieroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

//ViewModelFactory for MovieDetailsViewModel that takes in movie object as an argument
class MovieDetailsViewModelFactory(private val movie : Movie) : ViewModelProvider.Factory{

    //The create method's purpose is to create and return your view model.
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)){
            return MovieDetailsViewModel(movie) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}