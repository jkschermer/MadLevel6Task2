package com.example.madlevel6task2.service

import com.example.madlevel6task2.model.MovieDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("/3/discover/movie?")
    fun getMovies(@Query("api_key") apiKey: String,
                  @Query("language") language: String,
                  @Query("sort_by") popularity: String,
                  @Query("include_adult") includeAdult: Boolean,
                  @Query("include_video") includeVideo: Boolean,
                  @Query("page") page: Int,
                  @Query("primary_release_year") year: String
    ): Call<MovieDetail>
}