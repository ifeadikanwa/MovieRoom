package com.example.movieroom

import com.google.gson.annotations.SerializedName

//movie data class that hold properties every movie displayed should have
//Going to be used by our Gson converter to deserialize the Json results
data class Movie(
        @SerializedName("results")
        val results : ArrayList<Result>

        )

data class Result(
        @SerializedName("id")
        val id : Int,

        @SerializedName("title")
        val title : String,

        @SerializedName("genre_ids")
        val genre : List<Int>,

        @SerializedName("release_date")
        val releaseDate : String,

        @SerializedName("original_language")
        val language : String,

        @SerializedName("vote_average")
        val votingAvg : Double,

        @SerializedName("vote_count")
        val voteCount : Int,

        @SerializedName("overview")
        val overview : String,

        @SerializedName("poster_path")
        val posterUrl : String
        )
