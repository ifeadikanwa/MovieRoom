package com.example.movieroom

import com.squareup.moshi.Json

//movie data class that hold properties every movie displayed should have
data class Movie(
        val id : Int,

        val title : String,

        @Json(name = "genre_ids")
        val genre : List<Int>,

        @Json(name = "release_date")
        val releaseDate : String,

        @Json(name = "original_language")
        val language : String,

        @Json(name = "vote_average")
        val votingAvg : Int,

        @Json(name = "vote_count")
        val voteCount : Int,

        val overview : String,

        @Json(name = "poster_path")
        val posterUrl : String
        )
