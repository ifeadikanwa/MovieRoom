package com.example.movieroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception


class HomeViewModel : ViewModel() {
    //live data for api response which is a list of movies
    private var _movies = MutableLiveData<List<Movie>>()
    val movies : LiveData<List<Movie>>
        get() = _movies

    //live data for status of data fetching
    private var _status = MutableLiveData<MovieApiStatus>()
    val status : LiveData<MovieApiStatus>
        get() = _status

    //livedata for navigating to the selected movie details screen
    private var _navigateToSelectedMovie = MutableLiveData<Movie>()
    val navigateToSelectedMovie : LiveData<Movie>
        get() = _navigateToSelectedMovie


    //method that takes the selected movie and sets it to _navigateToSelectedMovie livedata
    //to start the navigation process
    fun displayMovieDetails(movie : Movie) {
        _navigateToSelectedMovie.value = movie
    }

    //when the navigation process is complete we want to set our _navigateToSelectedMovie
    //to null to prevent any extra unwanted navigation
    fun displayMovieDetailsComplete() {
        _navigateToSelectedMovie.value = null
    }

    //when the home fragment is on the screen get and display trending movies
    init {
        getTrendingMovies()
    }

    //fetch trending movies
    private fun getTrendingMovies() {
        // Coroutine that will be canceled when the ViewModel is cleared.
        viewModelScope.launch {
            try {
                //set status to loading before we attempt to fetch data
                _status.value = MovieApiStatus.LOADING

                //get trending movies
                var response = MovieApi.retrofitService.getTrendingMovies()

                //set status to done after we successfully fetch data
                _status.value = MovieApiStatus.DONE

                //store the arraylist of movies in live data
                _movies.value = response.movies
            }
            catch (e : Exception) {
                //set status to error if we get an exception
                _status.value = MovieApiStatus.ERROR

                //set the movie arraylist to an empty list to clear the recyclerview
                _movies.value =  ArrayList()

                Timber.i(e.toString())
            }
        }

    }
}