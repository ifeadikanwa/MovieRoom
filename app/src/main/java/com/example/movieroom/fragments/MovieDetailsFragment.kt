package com.example.movieroom.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.movieroom.fragments.MovieDetailsFragmentArgs
import com.example.movieroom.viewmodels.MovieDetailsViewModel
import com.example.movieroom.viewmodels.MovieDetailsViewModelFactory
import com.example.movieroom.R
import com.example.movieroom.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment() {
   lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //set action bar title
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.movie_details)

        // Inflate the layout for this fragment
        binding = FragmentMovieDetailsBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        //get the movie argument
        val movie = MovieDetailsFragmentArgs.fromBundle(requireArguments()).selectedMovie

        //create viewModelFactory and pass in movie
        val viewModelFactory = MovieDetailsViewModelFactory(movie)

        //use the viewModelFactory to create the viewModel
        var viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailsViewModel::class.java)

        //Give the binding access to the viewModel
        binding.detailsViewModel = viewModel

        return binding.root
    }


}