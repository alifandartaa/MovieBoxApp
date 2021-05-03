package com.example.movieboxapp.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieboxapp.data.source.entity.TvShowEntity
import com.example.movieboxapp.databinding.ItemTvshowBinding
import com.example.movieboxapp.ui.detail.DetailActivity
import com.example.movieboxapp.ui.movies.MoviesAdapter

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    companion object {
        const val EXTRA_TYPE = "EXTRA_TYPE"
        const val EXTRA_ID = "EXTRA_ID"
        const val TV_SHOW_TYPE = "TV_SHOW_TYPE"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }

    private var listTvshow = ArrayList<TvShowEntity>()

    fun setTvshows(tvshows : List<TvShowEntity>?){
        if(tvshows == null) return
        this.listTvshow.clear()
        this.listTvshow.addAll(tvshows)
    }

    class TvShowViewHolder(private val binding: ItemTvshowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: TvShowEntity) {
            with(binding){
                tvItemTitle.text = tvshow.title
                tvItemDesc.text = tvshow.description
                Glide.with(itemView.context)
                        .load(IMAGE_URL + tvshow.imagePath)
                        .into(imgPoster)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(EXTRA_ID, tvshow.tvshowId)
                    intent.putExtra(EXTRA_TYPE, TV_SHOW_TYPE)
                    itemView.context.startActivity(intent)
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowAdapter.TvShowViewHolder {
        val itemTvshowBinding = ItemTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemTvshowBinding)
    }

    override fun onBindViewHolder(holder: TvShowAdapter.TvShowViewHolder, position: Int) {
        val tvshow = listTvshow[position]
        holder.bind(tvshow)
    }

    override fun getItemCount(): Int {
        return listTvshow.size
    }

}