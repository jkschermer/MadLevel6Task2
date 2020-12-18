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