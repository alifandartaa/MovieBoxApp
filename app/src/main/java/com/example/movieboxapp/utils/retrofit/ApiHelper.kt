package com.example.movieboxapp.utils.retrofit

import android.util.Log
import com.example.movieboxapp.BuildConfig
import com.example.movieboxapp.data.source.remote.response.ResponseMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiHelper(private val apiInterface: ApiInterface) {
    private val TAG = ApiHelper::class.java.toString()
    val api_key = BuildConfig.ApiKey

    companion object {
        @Volatile
        private var instance: ApiHelper? = null

        fun getInstance(apiInterface: ApiInterface): ApiHelper = instance ?: synchronized(this){
            instance ?: ApiHelper(apiInterface)
        }
    }

//    fun getAllMovies(): {
//        ApiClient.getApiInterface().getMovies(api_key).enqueue(object : Callback<ResponseMovie> {
//            override fun onResponse(call: Call<ResponseMovie>, response: Response<ResponseMovie>) {
//                callback.getAllMoviesAsync(response.body()?.results)
//                EspressoIdlingResource.decrement()
//            }
//
//            override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {
//                Log.d(TAG, "onResponse: $it")
//            }
//        })
//    }
}