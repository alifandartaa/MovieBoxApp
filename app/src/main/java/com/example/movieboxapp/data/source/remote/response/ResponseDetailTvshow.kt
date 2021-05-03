package com.example.movieboxapp.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseDetailTvshow(

        @field:SerializedName("id")
        val id: Int,

        @field:SerializedName("first_air_date")
        val firstAirDate: String,

        @field:SerializedName("overview")
        val overview: String,

        @field:SerializedName("poster_path")
        val posterPath: String,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("tagline")
        val tagline: String,

        @field:SerializedName("status")
        val status: String
)


