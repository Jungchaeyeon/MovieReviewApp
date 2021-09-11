package com.jcy.moviereviewapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jcy.moviereviewapp.data.models.MovieItem
import com.jcy.moviereviewapp.databinding.ItemMovieBinding

class MovieAdapter(private val itemClick: (MovieItem) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private val items: ArrayList<MovieItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ItemMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )
        val viewHolder = ViewHolder(binding)
        binding.root.setOnClickListener {
            itemClick(items[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let {
            holder.bind(it)
        }
    }

    class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieItem) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }

    fun addItems(items: List<MovieItem>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }
}