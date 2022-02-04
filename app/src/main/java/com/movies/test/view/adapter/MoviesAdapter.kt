package com.movies.test.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.movies.test.databinding.MovieItemBinding
import com.movies.test.model.MovieListItemModel
import com.movies.test.util.AppConstants

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MoviesAdapter(private val context: Context, private val movieList: ArrayList<MovieListItemModel>) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieViewHolder(MovieItemBinding.inflate(LayoutInflater.from(context),parent,false))
        Log.i("TAG",binding.toString())
        return binding
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Log.i("TAG",AppConstants.IMAGE_BASE + movieList[position].poster_path)
        Glide.with(context)
            .load(AppConstants.IMAGE_BASE + movieList[position].poster_path)
            .listener(object:RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.binding.progress.visibility = View.INVISIBLE
                    holder.binding.movieImg.setImageDrawable(resource)
                    return true
                }

            })
            .into(holder.binding.movieImg)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun addMovies(movies: ArrayList<MovieListItemModel>) {
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}