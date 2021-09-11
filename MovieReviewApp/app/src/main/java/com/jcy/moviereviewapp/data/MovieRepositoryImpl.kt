package com.jcy.moviereviewapp.data

import com.jcy.moviereviewapp.data.models.MovieItem
import io.reactivex.Flowable
import io.reactivex.Single

class MovieRepositoryImpl(
    private val searchMovieItems : SearchMovieItems
): MovieRepository{
    override fun getSearchMovies(query: String): Single<List<MovieItem>> {
        return searchMovieItems.getSearchMovies(query)
            .flatMap {
                Single.just(it.movieItems)
            }
    }


}