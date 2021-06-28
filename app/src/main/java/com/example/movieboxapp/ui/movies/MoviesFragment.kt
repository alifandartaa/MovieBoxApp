package com.example.movieboxapp.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieboxapp.data.source.entity.MovieEntity
import com.example.movieboxapp.databinding.FragmentMoviesBinding
import com.example.movieboxapp.viewmodel.MoviesVMFactory
import com.example.movieboxapp.vo.Status

class MoviesFragment : Fragment() {

    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding
    private var listMovie: ArrayList<MovieEntity> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showLoading(true)
            val factory = MoviesVMFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]
            loadPopularMovies(viewModel)
        }
    }

    private fun loadPopularMovies(viewModel: MoviesViewModel) {
        val moviesAdapter = MoviesAdapter()
        viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                when (movies.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        showLoading(false)
                        moviesAdapter.setMovies(movies.data)
                        moviesAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        showLoading(false)
                    }
                }
            }
        })

        with(fragmentMoviesBinding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = moviesAdapter
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            fragmentMoviesBinding.progressBar.visibility = View.VISIBLE
        } else {
            fragmentMoviesBinding.progressBar.visibility = View.GONE
        }
    }

    fun getListMovie(): List<MovieEntity> {
        return listMovie
    }
}