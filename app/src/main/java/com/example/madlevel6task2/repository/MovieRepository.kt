package com.example.madlevel6task2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel6task2.BuildConfig.API_KEY
import com.example.madlevel6task2.database.MovieApi
import com.example.madlevel6task2.model.MovieDetail
import com.example.madlevel6task2.service.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {
    private val movieApiService: MovieApiService = MovieApi.createApi()

    private val _movies: MutableLiveData<List<MovieDetail.Result>> = MutableLiveData()

    /**
     *  Make the mutablelive data available via getter
     */

    val movies: LiveData<List<MovieDetail.Result>>
        get() = _movies

    /**
     *  Suspend function that calls the suspend function from the movie api call
     */

    suspend fun getMovies(inputYear: String) {
        val language = "en-US"
        val sortBy = "popularity.desc"
        val includeAdult = false
        val includeVideo = false
        val page = 1

        try {
            movieApiService.getMovies(API_KEY, language, sortBy, includeAdult, includeVideo, page, inputYear).enqueue(
                    object : Callback<MovieDetail> {
                        override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                            print(t)
                        }

                        override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                            val results = response.body()
                            val movieDetail = results?.results
                            _movies.value = movieDetail
                        }
                    }
            )

        } catch (error: Throwable) {
            throw MovieApiError("Unable to get data from api", error)
        }
    }


    class MovieApiError(message: String, cause: Throwable) : Throwable(message, cause)

}