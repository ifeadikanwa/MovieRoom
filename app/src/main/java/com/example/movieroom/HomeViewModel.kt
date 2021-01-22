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

    init {
        getTrendingMovies()
    }

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