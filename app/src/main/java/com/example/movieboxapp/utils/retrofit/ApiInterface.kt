package com.example.movieboxapp.utils.retrofit

import com.example.movieboxapp.data.source.remote.response.ResponseDetailMovie
import com.example.movieboxapp.data.source.remote.response.ResponseDetailTvshow
import com.example.movieboxapp.data.source.remote.response.ResponseMovie
import com.example.movieboxapp.data.source.remote.response.ResponseTvshow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/popular")
    fun getMovies(@Query("api_key") api: String): Call<ResponseMovie>

    @GET("tv/popular")
    fun getTvshows(@Query("api_key") api: String): Call<ResponseTvshow>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api: String
    ): Call<ResponseDetailMovie>

    @GET("tv/{tv_id}")
    fun getDetailTvshow(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api: String
    ): Call<ResponseDetailTvshow>
}