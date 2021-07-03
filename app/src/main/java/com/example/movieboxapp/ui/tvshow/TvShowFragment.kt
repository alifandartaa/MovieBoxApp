package com.example.movieboxapp.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieboxapp.databinding.FragmentTvshowBinding
import com.example.movieboxapp.viewmodel.MoviesVMFactory
import com.example.movieboxapp.vo.Status

class TvShowFragment : Fragment() {

    private var _fragmentTvShowBinding: FragmentTvshowBinding? = null
    private val binding get() = _fragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentTvShowBinding = FragmentTvshowBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showLoading(true)
            val factory = MoviesVMFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
            loadPopularTvShow(viewModel)
        }
    }

    private fun loadPopularTvShow(viewModel: TvShowViewModel) {
        val tvShowAdapter = TvShowAdapter()
        viewModel.getTvshow().observe(viewLifecycleOwner, { tvshows ->
            if (tvshows != null) {
                when (tvshows.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        tvShowAdapter.submitList(tvshows.data)
                        showLoading(false)
                        tvShowAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        showLoading(false)
                    }
                }
            }


        })

        binding?.let {
            with(it.rvTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding?.progressBar?.visibility = View.VISIBLE
        } else {
            binding?.progressBar?.visibility = View.GONE
        }
    }
}