package com.example.movie.apiManager

import com.example.movie.apiManager.models.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE = "https://moviesapi.ir/api/v1/"

class ApiManager {
    private val apiService: ApiService

    init {

        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        apiService = retrofit.create(ApiService::class.java)
    }

    fun getMovies(callBack: ApiCallBack<Movies>) {

        apiService.getMovies(callBack.number()).enqueue(object : Callback<Movies> {

            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                callBack.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                callBack.onError(t.message.toString())
            }

        })
    }

    interface ApiCallBack<T> {
        fun number(): Int
        fun onSuccess(data: T)
        fun onError(errorMessage: String)
    }
}