package com.jcy.moviereviewapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcy.moviereviewapp.base.BaseViewModel
import com.jcy.moviereviewapp.data.MovieRepository
import com.jcy.moviereviewapp.data.models.MovieItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class MovieSearchViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {

    private var currentQuery: String = ""
    val query = MutableLiveData<String>()
    private val _movieList = MutableLiveData<ArrayList<MovieItem>>()
    private val _toastMsg = MutableLiveData<MessageSet>()
    private val _isLoading = MutableLiveData<Boolean>(false)

    val movieList: LiveData<ArrayList<MovieItem>> get() = _movieList
    val toastMsg: LiveData<MessageSet> get() = _toastMsg
    val isLoading: LiveData<Boolean> get() = _isLoading


    fun requestMovie() {
        currentQuery = query.value.toString().trim()
        if (currentQuery.isEmpty()) {
            _toastMsg.value = MessageSet.EMPTY_QUERY
        } else {
            compositeDisposable.add(
                movieRepository.getSearchMovies(currentQuery)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { showProgress() }
                    .doAfterTerminate { hideProgress() }
                    .subscribe({ movies ->
                        if (movies.isEmpty()) {
                            _toastMsg.value = MessageSet.NO_RESULT
                        } else {
                            _movieList.value = movies as ArrayList<MovieItem>?
                            _toastMsg.value = MessageSet.SUCCESS
                        }
                    }, {
                        _toastMsg.value = MessageSet.NETWORK_ERROR
                    })
            )
        }
    }

    private fun showProgress() {
        _isLoading.value = true
    }

    private fun hideProgress() {
        _isLoading.value = false
    }

    enum class MessageSet {
        LAST_PAGE,
        EMPTY_QUERY,
        NETWORK_ERROR,
        SUCCESS,
        NO_RESULT
    }
}