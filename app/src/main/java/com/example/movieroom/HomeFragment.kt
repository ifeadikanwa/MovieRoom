package com.example.movieroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.movieroom.databinding.DisplayMoviesListItemBinding
import com.example.movieroom.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        //initialize view model
        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Giving the binding access to the HomeViewModel
        binding.homeViewModel = viewModel

        //set the recyclerview adapter
        binding.trendingRecyclerView.adapter = DisplayMoviesAdapter()

        return binding.root
    }


}