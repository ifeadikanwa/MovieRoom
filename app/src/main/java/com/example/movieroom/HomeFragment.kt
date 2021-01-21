package com.example.movieroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.movieroom.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)

        //set action bar title
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        //initialize view model
        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Giving the binding access to the HomeViewModel
        binding.homeViewModel = viewModel

        //set the recyclerview adapter
        binding.trendingRecyclerView.adapter = DisplayMoviesAdapter()

        //set onClickListener for all discover buttons
        binding.actionButton.setOnClickListener(discoverButtonClickListener)
        binding.adventureButton.setOnClickListener(discoverButtonClickListener)
        binding.animationButton.setOnClickListener(discoverButtonClickListener)
        binding.comedyButton.setOnClickListener(discoverButtonClickListener)
        binding.crimeButton.setOnClickListener(discoverButtonClickListener)
        binding.dramaButton.setOnClickListener(discoverButtonClickListener)
        binding.familyButton.setOnClickListener(discoverButtonClickListener)
        binding.fantasyButton.setOnClickListener(discoverButtonClickListener)
        binding.horrorButton.setOnClickListener(discoverButtonClickListener)
        binding.mysteryButton.setOnClickListener(discoverButtonClickListener)
        binding.thrillerButton.setOnClickListener(discoverButtonClickListener)
        binding.romanceButton.setOnClickListener(discoverButtonClickListener)

        return binding.root
    }

    //onClickListener for all the discover buttons
    //it identifies the button that was clicked and then
    //navigates to the discover fragment with an argument
    //containing information about what genre the user wants to see
    private val discoverButtonClickListener = View.OnClickListener {
        //enum variable that will hold the genre type depending on the button clicked
        var genre : Genre = Genre.Action

        //assign enum value depending on button clicked
        when(it){
            binding.actionButton -> genre = Genre.Action
            binding.adventureButton -> genre = Genre.Adventure
            binding.animationButton -> genre = Genre.Animation
            binding.comedyButton -> genre = Genre.Comedy
            binding.crimeButton -> genre = Genre.Crime
            binding.dramaButton -> genre = Genre.Drama
            binding.familyButton -> genre = Genre.Family
            binding.fantasyButton -> genre = Genre.Fantasy
            binding.horrorButton -> genre = Genre.Horror
            binding.mysteryButton -> genre = Genre.Mystery
            binding.romanceButton -> genre = Genre.Romance
            binding.thrillerButton -> genre = Genre.Thriller
        }

        //use the view(button in this case) to find the nav controller and then proceed to navigate with the genre as an argument
        it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDiscoverFragment(genre))
    }




}