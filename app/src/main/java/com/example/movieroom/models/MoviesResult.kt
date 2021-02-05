package com.example.movieroom

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

//movie data class that hold properties every movie displayed should have
//Going to be used by our Gson converter to deserialize the Json results
data class MoviesResult(
        @SerializedName("results")
        val movies : ArrayList<Movie>
        )

//we want to be able to share Movie class properties between fragments,
//this means it need to be in a format that can be passed as an argument,
//so we parcelize (Android's way of turning an object into a stream of data) it
//so it can be put in a bundle that will be shared between fragments
@Parcelize
data class Movie(
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
        ) : Parcelable {

        }
