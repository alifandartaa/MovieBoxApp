package com.example.movieboxapp.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseDetailMovie(

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("tagline")
	val tagline: String,

	@field:SerializedName("status")
	val status: String
)
