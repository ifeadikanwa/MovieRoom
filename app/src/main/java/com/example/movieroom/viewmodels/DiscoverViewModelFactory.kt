package com.example.movieroom.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieroom.utils.Genre
import java.lang.IllegalArgumentException

//ViewModelFactory for DiscoverViewModel that takes in Genre enum object as an argument
class DiscoverViewModelFactory(private val genre : Genre) : ViewModelProvider.Factory{

    //The create method's purpose is to create and return your view model.
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DiscoverViewModel::class.java)){
            return DiscoverViewModel(genre) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}