package com.jcy.moviereviewapp.data

import com.jcy.moviereviewapp.data.models.MovieItems
import io.reactivex.Single

interface SearchMovieItems {
    fun getSearchMovies(
        query: String
    ): Single<MovieItems>
}