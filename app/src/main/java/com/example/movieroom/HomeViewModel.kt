package com.example.movieroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception


class HomeViewModel : ViewModel() {
    //live data for api response
    private var _result = MutableLiveData<Movie>()
    val result : LiveData<Movie>
        get() = _result

    init {
        getTrendingMovies()
    }

    private fun getTrendingMovies() {
        // Coroutine that will be canceled when the ViewModel is cleared.
        viewModelScope.launch {
            try {
                //get trending movies and store it in our live data
                _result.value = MovieApi.retrofitService.getTrendingMovies()
            }
            catch (e : Exception) {
                Timber.i(e.toString())
            }
        }

    }
}