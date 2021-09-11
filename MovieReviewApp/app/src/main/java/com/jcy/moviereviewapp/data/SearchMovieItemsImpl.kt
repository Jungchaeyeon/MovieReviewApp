package com.jcy.moviereviewapp.data

import com.jcy.moviereviewapp.api.ApiInterface
import com.jcy.moviereviewapp.data.models.MovieItems
import io.reactivex.Single

class SearchMovieItemsImpl(private val apiInterface: ApiInterface):
    SearchMovieItems{
    override fun getSearchMovies(query: String): Single<MovieItems> {
        return apiInterface.getSearchMovie(query).map {
            it
        }
    }

}