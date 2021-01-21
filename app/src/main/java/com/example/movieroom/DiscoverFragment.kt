package com.example.movieroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.movieroom.databinding.FragmentDiscoverBinding


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

        return binding.root
    }


}