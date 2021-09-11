package com.jcy.moviereviewapp.api

import com.jcy.moviereviewapp.data.models.MovieItems
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("v1/search/movie.json")
    fun getSearchMovie(
        @Query("query") query: String,
        @Query("display") display: Int = 20
    ): Single<MovieItems>
}