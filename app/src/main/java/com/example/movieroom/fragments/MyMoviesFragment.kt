package com.example.movieroom.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.movieroom.R


class MyMoviesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //set action bar title
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.my_movies)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_movies, container, false)
    }


}