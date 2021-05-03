package com.example.movieboxapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movieboxapp.databinding.ActivityDetailBinding
import com.example.movieboxapp.ui.movies.MoviesAdapter
import com.example.movieboxapp.viewmodel.MoviesVMFactory

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "EXTRA_ID"
        const val EXTRA_TYPE = "EXTRA_TYPE"
        const val MOVIE_TYPE = "MOVIE_TYPE"
        const val TV_SHOW_TYPE = "TV_SHOW_TYPE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)

        val idEntity = intent.getIntExtra(EXTRA_ID, 0)
        val typeEntity = intent.getStringExtra(EXTRA_TYPE)

        val factory = MoviesVMFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        if (typeEntity.equals(MOVIE_TYPE)) {
            activityDetailBinding.progressBar.visibility = View.VISIBLE
            viewModel.selectId(idEntity)
            viewModel.getDetailMovie()?.observe(this, { detailMovie ->
                activityDetailBinding.progressBar.visibility = View.GONE
                if (detailMovie != null) {
                    activityDetailBinding.detailTitle.text = detailMovie.title
                    activityDetailBinding.detailStatusValue.text = detailMovie.status
                    activityDetailBinding.detailReleaseValue.text = detailMovie.releaseDate
                    activityDetailBinding.detailTagLineValue.text = detailMovie.tagline
                    activityDetailBinding.detailOverviewValue.text = detailMovie.description
                    Glide.with(baseContext)
                            .load(MoviesAdapter.IMAGE_URL + detailMovie.imagePath)
                            .into(activityDetailBinding.imgPoster)
                }
            })
        }
        if (typeEntity.equals(TV_SHOW_TYPE)) {
            activityDetailBinding.progressBar.visibility = View.VISIBLE
            viewModel.selectId(idEntity)
            viewModel.getDetailTvshow()?.observe(this, { detailTvshow ->
                activityDetailBinding.progressBar.visibility = View.GONE
                if (detailTvshow != null) {
                    activityDetailBinding.detailTitle.text = detailTvshow.title
                    activityDetailBinding.detailStatusValue.text = detailTvshow.status
                    activityDetailBinding.detailReleaseValue.text = detailTvshow.releaseDate
                    activityDetailBinding.detailTagLineValue.text = detailTvshow.tagline
                    activityDetailBinding.detailOverviewValue.text = detailTvshow.description
                    Glide.with(baseContext)
                            .load(MoviesAdapter.IMAGE_URL + detailTvshow.imagePath)
                            .into(activityDetailBinding.imgPoster)
                }
            })
        }

    }
}