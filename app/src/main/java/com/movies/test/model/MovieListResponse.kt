package com.movies.test.model

data class MovieListResponse(
    val page: Int, val results: ArrayList<MovieListItemModel>,
    val total_pages: Int, val total_results: Int
)