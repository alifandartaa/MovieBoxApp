package com.example.movieboxapp.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieboxapp.data.source.entity.DetailEntity
import com.example.movieboxapp.databinding.ItemBookmarkBinding

class BookmarkAdapter :
    PagedListAdapter<DetailEntity, BookmarkAdapter.BookmarkViewHolder>(DIFF_CALLBACK) {

    companion object {
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DetailEntity>() {
            override fun areItemsTheSame(oldItem: DetailEntity, newItem: DetailEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DetailEntity, newItem: DetailEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun getSwipedData(swipedPosition: Int): DetailEntity? = getItem(swipedPosition)

    class BookmarkViewHolder(private val binding: ItemBookmarkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bookmark: DetailEntity) {
            with(binding) {
                tvItemTitle.text = bookmark.title
                tvItemDesc.text = bookmark.description
                Glide.with(itemView.context)
                    .load(IMAGE_URL + bookmark.imagePath)
                    .into(imgPoster)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookmarkViewHolder {
        val itemBookmarkBinding =
            ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(itemBookmarkBinding)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val bookmark = getItem(position)
        if (bookmark != null) {
            holder.bind(bookmark)
        }
    }
}