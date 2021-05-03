package com.example.movieboxapp.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseTvshow(

    @field:SerializedName("results")
    val results: List<DataTvshow>,
)

data class DataTvshow(
    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)
