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

    //when the discover fragment is on screen get movies to display
    init {
        getMovies()
    }

    //function for fetching movie based on selected genre
    private fun getMovies() {
        // Coroutine that will be canceled when the ViewModel is cleared.
        viewModelScope.launch {
            try {
                //set status to loading before we attempt to fetch data
                _status.value = MovieApiStatus.LOADING

                //using the genre value that was passed into the viewModel,
                //identify the right movie genre and fetch the movies
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