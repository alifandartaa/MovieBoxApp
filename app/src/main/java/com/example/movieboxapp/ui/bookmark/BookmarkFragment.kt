package com.example.movieboxapp.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieboxapp.R
import com.example.movieboxapp.databinding.FragmentBookmarkBinding
import com.example.movieboxapp.viewmodel.MoviesVMFactory
import com.google.android.material.snackbar.Snackbar


class BookmarkFragment : Fragment() {

    private var _fragmentBookmarkBinding: FragmentBookmarkBinding? = null
    private val binding get() = _fragmentBookmarkBinding

    private lateinit var viewModel: BookmarkViewModel
    private lateinit var bookmarkAdapter: BookmarkAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentBookmarkBinding = FragmentBookmarkBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding?.rvBookmark)

        if (activity != null) {
            val factory = MoviesVMFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[BookmarkViewModel::class.java]
            loadBookmarkList(viewModel)
        }
    }

    private fun loadBookmarkList(viewModel: BookmarkViewModel) {
        bookmarkAdapter = BookmarkAdapter()
        binding?.progressBar?.visibility = View.VISIBLE
        viewModel.getBookmark().observe(viewLifecycleOwner, { bookmarks ->
            if (bookmarks != null) {
                binding?.progressBar?.visibility = View.GONE
                bookmarkAdapter.submitList(bookmarks)
            }
        })

        binding?.let {
            with(it.rvBookmark) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = bookmarkAdapter
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val detailEntity = bookmarkAdapter.getSwipedData(swipedPosition)
                detailEntity?.let { viewModel.setBookmark(it) }
                val snackbar =
                    Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    detailEntity?.let { viewModel.setBookmark(it) }
                }
                snackbar.show()
            }
        }
    })
}