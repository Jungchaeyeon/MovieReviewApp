package com.jcy.moviereviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.jcy.moviereviewapp.databinding.ActivityMainBinding
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var  movieAdapter: MovieAdapter
    private val compositeDisposable = CompositeDisposable()

    private val viewModel : MovieSearchViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.vm = viewModel
        binding.lifecycleOwner = this //binding에 lifecycleOwner는 꼭 붙여주어야 한다


        initViewModelCallback()
        initAdapters()
    }
    private fun initAdapters(){
        movieAdapter = MovieAdapter { movie->
            //Todo: 영화 상세 페이지로 이동
        }
        binding.movieItemsRv.adapter = movieAdapter
    }
    private fun initViewModelCallback(){
        with(viewModel){
            toastMsg.observe(this@MainActivity, Observer {
                when(toastMsg.value){
                    MovieSearchViewModel.MessageSet.LAST_PAGE -> showToast(getString(R.string.last_page_msg))
                    MovieSearchViewModel.MessageSet.EMPTY_QUERY -> showToast(getString(R.string.search_input_query_msg))
                    MovieSearchViewModel.MessageSet.NETWORK_ERROR -> showToast(getString(R.string.network_error_msg))
                    MovieSearchViewModel.MessageSet.SUCCESS -> showToast(getString(R.string.load_movie_success_msg))
                    MovieSearchViewModel.MessageSet.NO_RESULT -> showToast(getString(R.string.no_movie_error_msg))
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}