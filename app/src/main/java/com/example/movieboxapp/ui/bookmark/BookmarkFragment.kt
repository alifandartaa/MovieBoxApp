package com.example.movieboxapp.ui.bookmark

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieboxapp.databinding.FragmentBookmarkBinding
import com.example.movieboxapp.viewmodel.MoviesVMFactory


class BookmarkFragment : Fragment() {

    private lateinit var bookmarkBinding: FragmentBookmarkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bookmarkBinding = FragmentBookmarkBinding.inflate(layoutInflater, container, false)
        return bookmarkBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = MoviesVMFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[BookmarkViewModel::class.java]
            loadBookmarkList(viewModel)
        }
    }

    private fun loadBookmarkList(viewModel: BookmarkViewModel) {
        val bookmarkAdapter = BookmarkAdapter()
        viewModel.getBookmark().observe(viewLifecycleOwner, { bookmarks ->
            Log.d("BookmarksFragment", bookmarks.toString())
            if (bookmarks != null) {
                bookmarkAdapter.setBookmarkList(bookmarks)
                bookmarkAdapter.notifyDataSetChanged()
            }
        })

        with(bookmarkBinding.rvBookmark) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = bookmarkAdapter
        }
    }
}