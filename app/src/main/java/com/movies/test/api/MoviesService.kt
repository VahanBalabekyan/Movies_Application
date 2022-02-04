package com.movies.test.api

import com.movies.test.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("/3/movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): MovieListResponse
}