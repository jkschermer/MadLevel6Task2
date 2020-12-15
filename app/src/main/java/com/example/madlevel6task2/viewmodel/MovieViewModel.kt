package com.example.madlevel6task2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.madlevel6task2.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val movieRepository = MovieRepository()

    var movies = movieRepository.movies

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    /**
     *  Make the property available through the getter
     */

    val errorText: LiveData<String>
        get() = _errorText

    /**
     *   viewmodelscope is bound to dispatchers.main and will be canceled when the viewmodel
     *   is cleared
     */

    fun getMovies(inputYear: String) {
        viewModelScope.launch {
            try {
                // the repo sets its own livedata property
                // our own movie livedata property points to the one in the repo
                movieRepository.getMovies(inputYear)
            } catch (error: MovieRepository.MovieApiError) {
                _errorText.value = error.message
            }
        }
    }
}