package com.example.movieboxapp.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieboxapp.R
import com.example.movieboxapp.data.source.entity.TvShowEntity
import com.example.movieboxapp.data.source.remote.response.DataTvshow
import com.example.movieboxapp.databinding.FragmentTvshowBinding
import com.example.movieboxapp.viewmodel.MoviesVMFactory

class TvShowFragment : Fragment() {

    private lateinit var fragmentTvshowBinding: FragmentTvshowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvshowBinding = FragmentTvshowBinding.inflate(layoutInflater, container, false)
        return fragmentTvshowBinding.root
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
            tvShowAdapter.setTvshows(tvshows)
            showLoading(false)
            tvShowAdapter.notifyDataSetChanged()
        })

        with(fragmentTvshowBinding.rvTvshow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            fragmentTvshowBinding.progressBar.visibility = View.VISIBLE
        } else {
            fragmentTvshowBinding.progressBar.visibility = View.GONE
        }
    }
}