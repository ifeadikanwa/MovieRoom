package com.example.movieroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieroom.databinding.FragmentDiscoverBinding


class DiscoverFragment : Fragment() {
    lateinit var binding: FragmentDiscoverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDiscoverBinding.inflate(inflater)

        //retrieve argument
        val args = DiscoverFragmentArgs.fromBundle(requireArguments())
        val genre = args.genre

        //display genre in textview
        binding.genreTitle.text = genre.name

        return binding.root
    }


}