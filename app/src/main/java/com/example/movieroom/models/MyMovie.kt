package com.example.movieroom.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "MyMovies")
data class MyMovie(
        @PrimaryKey(autoGenerate = true)
        val entryNumber : Long,

        @ColumnInfo
        val id : Int,

        @ColumnInfo
        val title : String,

        @ColumnInfo
        val genre : List<Int>,

        @ColumnInfo
        val releaseDate : String,

        @ColumnInfo
        val language : String,

        @ColumnInfo
        val votingAvg : Double,

        @ColumnInfo
        val voteCount : Int,

        @ColumnInfo
        val overview : String,

        @ColumnInfo
        val posterUrl : String
)
