package com.example.movieboxapp.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieboxapp.data.source.entity.MovieEntity
import com.example.movieboxapp.databinding.ItemMoviesBinding
import com.example.movieboxapp.ui.detail.DetailActivity


class MoviesAdapter :
        RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    companion object {
        const val EXTRA_TYPE = "EXTRA_TYPE"
        const val EXTRA_ID = "EXTRA_ID"
        const val MOVIE_TYPE = "MOVIE_TYPE"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }

    private var listMovies = ArrayList<MovieEntity>()

    fun setMovies(movies: List<MovieEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    class MovieViewHolder(private val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemDesc.text = movie.description
                Glide.with(itemView.context)
                        .load(IMAGE_URL + movie.imagePath)
                        .into(imgPoster)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(EXTRA_ID, movie.movieId)
                    intent.putExtra(EXTRA_TYPE, MOVIE_TYPE)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMoviesBinding = ItemMoviesBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return MovieViewHolder(itemMoviesBinding)
    }

    override fun onBindViewHolder(viewHolder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        viewHolder.bind(movie)
    }

    override fun getItemCount() = listMovies.size

}