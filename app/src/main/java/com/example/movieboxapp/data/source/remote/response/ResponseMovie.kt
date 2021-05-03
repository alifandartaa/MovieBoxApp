package com.example.movieboxapp.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseMovie(

    @field:SerializedName("results")
    val results: List<DataMovie>
)

data class DataMovie(

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("poster_path")
    val posterPath: String
)
