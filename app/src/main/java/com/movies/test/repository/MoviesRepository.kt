package com.movies.test.repository


import com.movies.test.api.MoviesService
import com.movies.test.model.MovieListResponse


class MoviesRepository(private val moviesService: MoviesService) {

    suspend fun getPopularMovies(page: Int):MovieListResponse {
      return moviesService.getMovies("e737d929466b4afe61c6d2118119e7b7",page)
    }
}