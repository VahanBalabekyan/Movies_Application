package com.movies.test.view.activity

import androidx.lifecycle.MutableLiveData
import com.movies.test.model.MovieListResponse
import com.movies.test.repository.MoviesRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repository: MoviesRepository) : BaseViewModel() {
    val moviesLiveData = MutableLiveData<MovieListResponse>()
    val moviesErrorLiveData = MutableLiveData<Boolean>()

    fun getPopularMovies(page: Int) {
        launch {
            try {
                moviesLiveData.postValue(repository.getPopularMovies(page))

            } catch (e: Exception) {
                moviesErrorLiveData.postValue(true)
            }
        }

    }

}