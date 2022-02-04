package com.movies.test.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.movies.test.R
import com.movies.test.databinding.ActivityMainBinding
import com.movies.test.view.adapter.MoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private var mPage = 1
    private val mAdapter by lazy { MoviesAdapter(this, ArrayList()) }
    private lateinit var mBinding: ActivityMainBinding
    private val activityViewModel by viewModel<MainViewModel>()
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initAdapter()
        activityViewModel.getPopularMovies(mPage)
        initViewModelObserver()
    }

    private fun initAdapter() {
        mBinding.moviesRecyclerView.layoutManager = GridLayoutManager(applicationContext, 3)
        mBinding.moviesRecyclerView.adapter = mAdapter
        mBinding.moviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount: Int =
                    (mBinding.moviesRecyclerView.layoutManager as GridLayoutManager).childCount
                val totalItemCount: Int =
                    (mBinding.moviesRecyclerView.layoutManager as GridLayoutManager).itemCount
                val firstVisibleItemPosition: Int =
                    (mBinding.moviesRecyclerView.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()
                if (!isLoading) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {
                        activityViewModel.getPopularMovies(mPage + 1)
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun initViewModelObserver() {
        activityViewModel.moviesLiveData.observe(this, {
            mAdapter.addMovies(it.results)
            mPage = it.page
            isLoading = false
        })
        activityViewModel.moviesErrorLiveData.observe(this,{
            isLoading = false
            Toast.makeText(this@MainActivity,"Error,try again!",Toast.LENGTH_SHORT).show()
        })
    }
}
