package com.example.movieroom.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movieroom.*
import com.example.movieroom.adapters.DisplayMoviesAdapter
import com.example.movieroom.databinding.FragmentDiscoverBinding
import com.example.movieroom.viewmodels.DiscoverViewModel
import com.example.movieroom.viewmodels.DiscoverViewModelFactory


class DiscoverFragment : Fragment() {
    lateinit var binding: FragmentDiscoverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //set the action bar title to nothing while we wait for the argument to be retrieved
        (activity as AppCompatActivity).supportActionBar?.title = ""

        //retrieve argument
        val args = DiscoverFragmentArgs.fromBundle(requireArguments())
        val genre = args.genre

        //set action bar title to genre name
        (activity as AppCompatActivity).supportActionBar?.title = genre.name

        // Inflate the layout for this fragment
        binding = FragmentDiscoverBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        //create viewModelFactory and pass in genre
        val viewModelFactory = DiscoverViewModelFactory(genre)

        //use the viewModelFactory to create the viewModel
        var viewModel = ViewModelProvider(this, viewModelFactory).get(DiscoverViewModel::class.java)

        //Give the binding access to the viewModel
        binding.discoverViewModel = viewModel

        //set the recyclerview adapter
        binding.discoverRecyclerView.adapter = DisplayMoviesAdapter(DisplayMoviesAdapter.OnClickListener {
            viewModel.displayMovieDetails(it)
        })

        //observer for navigateToSelectedMovie that triggers navigation when it's not null
        //and then calls displayMovieDetailsComplete to avoid extra unwanted navigation
        viewModel.navigateToSelectedMovie.observe(viewLifecycleOwner, Observer {
            if(null != it) {
                this.findNavController().navigate(DiscoverFragmentDirections.actionDiscoverFragmentToMovieDetailsFragment(it))
                viewModel.displayMovieDetailsComplete()
            }
        })
        
        return binding.root
    }


}