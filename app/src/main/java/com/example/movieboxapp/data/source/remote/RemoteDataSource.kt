package com.example.movieboxapp.data.source.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieboxapp.BuildConfig
import com.example.movieboxapp.data.source.remote.response.*
import com.example.movieboxapp.utils.retrofit.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    private val apiKey = BuildConfig.ApiKey
    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            RemoteDataSource().apply { instance = this }
        }
    }

    fun getAllMovies(): LiveData<ApiResponse<ResponseMovie>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<ResponseMovie>>()
        ApiClient.getApiInterface().getMovies(apiKey).enqueue(object : Callback<ResponseMovie> {
            override fun onResponse(call: Call<ResponseMovie>, response: Response<ResponseMovie>) {
                handler.postDelayed({
                    resultMovies.value = ApiResponse.success(response.body()!!)
                    EspressoIdlingResource.decrement()
                }, SERVICE_LATENCY_IN_MILLIS)

            }

            override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {
                Log.d(this@RemoteDataSource.toString(), "get error : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        Log.d("REMOTE_DATA", "ResultMovies $resultMovies")
        return resultMovies
    }

    fun getAllTvshow(): LiveData<ApiResponse<ResponseTvshow>> {
        EspressoIdlingResource.increment()
        val resultTvshows = MutableLiveData<ApiResponse<ResponseTvshow>>()
        ApiClient.getApiInterface().getTvshows(apiKey).enqueue(object : Callback<ResponseTvshow> {
            override fun onResponse(
                call: Call<ResponseTvshow>,
                response: Response<ResponseTvshow>
            ) {
                handler.postDelayed({
                    resultTvshows.value = ApiResponse.success(response.body()!!)
                    EspressoIdlingResource.decrement()
                }, SERVICE_LATENCY_IN_MILLIS)
            }

            override fun onFailure(call: Call<ResponseTvshow>, t: Throwable) {
                Log.d(this@RemoteDataSource.toString(), "get error : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultTvshows
    }

    fun getMovieDetail(id: Int): LiveData<ApiResponse<ResponseDetailMovie>> {
        EspressoIdlingResource.increment()
        val resultDetailMovie = MutableLiveData<ApiResponse<ResponseDetailMovie>>()
        ApiClient.getApiInterface().getDetailMovie(id, apiKey)
            .enqueue(object : Callback<ResponseDetailMovie> {
                override fun onResponse(
                    call: Call<ResponseDetailMovie>,
                    responseMovie: Response<ResponseDetailMovie>
                ) {
                    handler.postDelayed({
                        resultDetailMovie.value = ApiResponse.success(responseMovie.body()!!)
                        EspressoIdlingResource.decrement()
                    }, SERVICE_LATENCY_IN_MILLIS)
                }

                override fun onFailure(call: Call<ResponseDetailMovie>, t: Throwable) {
                    Log.d(this@RemoteDataSource.toString(), "get error : ${t.message}")
                    EspressoIdlingResource.decrement()
                }
            })
        return resultDetailMovie
    }

    fun getTvshowDetail(id: Int): LiveData<ApiResponse<ResponseDetailTvshow>> {
        EspressoIdlingResource.increment()
        val resultDetailTvshow = MutableLiveData<ApiResponse<ResponseDetailTvshow>>()
        ApiClient.getApiInterface().getDetailTvshow(id, apiKey)
            .enqueue(object : Callback<ResponseDetailTvshow> {
                override fun onResponse(
                    call: Call<ResponseDetailTvshow>,
                    response: Response<ResponseDetailTvshow>
                ) {
                    handler.postDelayed({
                        resultDetailTvshow.value = ApiResponse.success(response.body()!!)
                        EspressoIdlingResource.decrement()
                    }, SERVICE_LATENCY_IN_MILLIS)
                }

                override fun onFailure(call: Call<ResponseDetailTvshow>, t: Throwable) {
                    Log.d(this@RemoteDataSource.toString(), "get error : ${t.message}")
                    EspressoIdlingResource.decrement()
                }
            })
        return resultDetailTvshow
    }
}