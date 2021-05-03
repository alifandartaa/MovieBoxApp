package com.example.movieboxapp.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieboxapp.data.source.entity.MovieEntity
import com.example.movieboxapp.data.source.remote.response.DataMovie
import com.example.movieboxapp.databinding.FragmentMoviesBinding
import com.example.movieboxapp.viewmodel.MoviesVMFactory

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
            moviesAdapter.setMovies(movies)
            listMovie.addAll(movies)
            showLoading(false)
            moviesAdapter.notifyDataSetChanged()
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

    fun getListMovie() : List<MovieEntity>{
        return listMovie
    }
}