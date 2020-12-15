package com.example.madlevel6task2.database

import com.example.madlevel6task2.service.MovieApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApi {
    companion object {
        // base url of the api
        private const val BASEURL = "https://api.themoviedb.org"
        val API_KEY = "87ab60fedd6f3cef8d4bb07296f9e564"
        val PAGE = 1
        val INCLUDE_VIDEO = false
        val INCLUDE_ADULT = false


        // service class of the api
        fun createApi(): MovieApiService {
            // create okhttp client to be able to make a log
            val okHttpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)).build()

            // create the retrofit instance
            val movieApi = Retrofit.Builder().baseUrl(BASEURL).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build()

            // return the retrofit service
            return movieApi.create(MovieApiService::class.java)
        }
    }
}