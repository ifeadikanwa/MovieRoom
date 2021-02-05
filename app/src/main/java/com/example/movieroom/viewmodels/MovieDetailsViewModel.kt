package com.example.movieroom.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieroom.Movie

class MovieDetailsViewModel(movie : Movie) : ViewModel() {
    //LiveData for the selected movie to be displayed
    private var _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie : LiveData<Movie>
        get() = _selectedMovie

    //initialize the movie livedata with the movie object passed into the viewmodel
    init {
        _selectedMovie.value = movie
    }
}