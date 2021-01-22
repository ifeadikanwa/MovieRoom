package com.example.movieroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class DiscoverViewModel(val genre: Genre) : ViewModel() {
    //live data for api response which is a list of movies
    private var _movies = MutableLiveData<List<Movie>>()
    val movies : LiveData<List<Movie>>
        get() = _movies

    //live data for status of data fetching
    private var _status = MutableLiveData<String>()
    val status : LiveData<String>
        get() = _status

    init {
        getMovies()
    }

    private fun getMovies() {
        // Coroutine that will be canceled when the ViewModel is cleared.
        viewModelScope.launch {
            try {
                //using the genre value that was passed into the viewModel, identify the movie genre and fetch the movies
                var response = when(genre){
                    Genre.Action -> MovieApi.retrofitService.getActionMovies()
                    Genre.Adventure -> MovieApi.retrofitService.getAdventureMovies()
                    Genre.Animation -> MovieApi.retrofitService.getAnimationMovies()
                    Genre.Comedy -> MovieApi.retrofitService.getComedyMovies()
                    Genre.Crime -> MovieApi.retrofitService.getCrimeMovies()
                    Genre.Drama -> MovieApi.retrofitService.getDramaMovies()
                    Genre.Family -> MovieApi.retrofitService.getFamilyMovies()
                    Genre.Fantasy -> MovieApi.retrofitService.getFantasyMovies()
                    Genre.Horror -> MovieApi.retrofitService.getHorrorMovies()
                    Genre.Mystery -> MovieApi.retrofitService.getMysteryMovies()
                    Genre.Romance -> MovieApi.retrofitService.getRomanceMovies()
                    Genre.Thriller -> MovieApi.retrofitService.getThrillerMovies()
                }

                //store the arraylist of movies in live data
                _movies.value = response.movies
            }
            catch (e : Exception) {
                Timber.i(e.toString())
            }
        }
    }
}