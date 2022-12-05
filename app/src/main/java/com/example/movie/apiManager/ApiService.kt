package com.example.movie.apiManager

import com.example.movie.apiManager.models.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movies")
    fun getMovies(@Query("page") queryParam: Int): Call<Movies>
}