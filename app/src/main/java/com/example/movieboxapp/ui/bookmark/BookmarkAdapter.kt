package com.example.movieboxapp.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieboxapp.data.source.entity.DetailEntity
import com.example.movieboxapp.databinding.ItemBookmarkBinding

class BookmarkAdapter : RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {

    companion object {
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }

    private var listBookmark = ArrayList<DetailEntity>()

    fun setBookmarkList(bookmark: List<DetailEntity>) {
        if (bookmark == null) return
        this.listBookmark.clear()
        this.listBookmark.addAll(bookmark)
    }

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
    ): BookmarkAdapter.BookmarkViewHolder {
        val itemBookmarkBinding =
            ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(itemBookmarkBinding)
    }

    override fun onBindViewHolder(holder: BookmarkAdapter.BookmarkViewHolder, position: Int) {
        val bookmark = listBookmark[position]
        holder.bind(bookmark)
    }

    override fun getItemCount(): Int = listBookmark.size

}