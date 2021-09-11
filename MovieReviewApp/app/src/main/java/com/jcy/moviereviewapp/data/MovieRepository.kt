package com.jcy.moviereviewapp.data

import com.jcy.moviereviewapp.data.models.MovieItem
import io.reactivex.Single

interface MovieRepository {
    fun getSearchMovies(
        query: String
    ): Single<List<MovieItem>>
}