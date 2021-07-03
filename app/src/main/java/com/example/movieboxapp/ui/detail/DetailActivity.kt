package com.example.movieboxapp.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movieboxapp.R
import com.example.movieboxapp.databinding.ActivityDetailBinding
import com.example.movieboxapp.ui.movies.MoviesAdapter
import com.example.movieboxapp.viewmodel.MoviesVMFactory
import com.example.movieboxapp.vo.Status

class DetailActivity : AppCompatActivity() {

    private var _activityDetailBinding: ActivityDetailBinding? = null
    private val binding get() = _activityDetailBinding

    private var menu: Menu? = null
    private lateinit var viewModel: DetailViewModel
    private var typeEntity: String? = ""

    companion object {
        const val EXTRA_ID = "EXTRA_ID"
        const val EXTRA_TYPE = "EXTRA_TYPE"
        const val MOVIE_TYPE = "MOVIE_TYPE"
        const val TV_SHOW_TYPE = "TV_SHOW_TYPE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val idEntity = intent.getIntExtra(EXTRA_ID, 0)
        typeEntity = intent.getStringExtra(EXTRA_TYPE)

        val factory = MoviesVMFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        if (typeEntity.equals(MOVIE_TYPE)) {
            viewModel.selectId(idEntity)
            viewModel.detailMovie.observe(this, { detailMovie ->
                if (detailMovie != null) {
                    when (detailMovie.status) {
                        Status.LOADING -> binding?.progressBar?.visibility =
                            View.VISIBLE
                        Status.SUCCESS -> if (detailMovie.data != null) {
                            binding?.progressBar?.visibility = View.GONE
                            binding?.detailTitle?.text = detailMovie.data.title
                            binding?.tvDetailStatusValue?.text = detailMovie.data.status
                            binding?.tvDetailReleaseValue?.text =
                                detailMovie.data.releaseDate
                            binding?.tvDetailTaglineValue?.text = detailMovie.data.tagline
                            binding?.tvDetailOverviewValue?.text =
                                detailMovie.data.description
                            Glide.with(baseContext)
                                .load(MoviesAdapter.IMAGE_URL + detailMovie.data.imagePath)
                                .into(binding?.imgPoster!!)
                        }
                        Status.ERROR -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(
                                applicationContext,
                                "Terjadi kesalahan",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
        }
        if (typeEntity.equals(TV_SHOW_TYPE)) {
            viewModel.selectId(idEntity)
            viewModel.detailTvShow.observe(this, { detailTvShow ->
                if (detailTvShow != null) {
                    when (detailTvShow.status) {
                        Status.LOADING -> binding?.progressBar?.visibility =
                            View.VISIBLE
                        Status.SUCCESS -> if (detailTvShow.data != null) {
                            binding?.progressBar?.visibility = View.GONE
                            binding?.detailTitle?.text = detailTvShow.data.title
                            binding?.tvDetailStatusValue?.text = detailTvShow.data.status
                            binding?.tvDetailReleaseValue?.text =
                                detailTvShow.data.releaseDate
                            binding?.tvDetailTaglineValue?.text =
                                detailTvShow.data.tagline
                            binding?.tvDetailOverviewValue?.text =
                                detailTvShow.data.description
                            Glide.with(baseContext)
                                .load(MoviesAdapter.IMAGE_URL + detailTvShow.data.imagePath)
                                .into(binding?.imgPoster!!)
                        }
                        Status.ERROR -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(
                                applicationContext,
                                "Terjadi kesalahan",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        if (typeEntity.equals(MOVIE_TYPE)) {
            viewModel.detailMovie.observe(this, { detailMovie ->
                if (detailMovie != null) {
                    when (detailMovie.status) {
                        Status.LOADING -> binding?.progressBar?.visibility =
                            View.VISIBLE
                        Status.SUCCESS -> if (detailMovie.data != null) {
                            binding?.progressBar?.visibility =
                                View.GONE
                            val state = detailMovie.data.bookmarked
                            setBookmarkState(state)
                        }
                        Status.ERROR -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(
                                applicationContext,
                                "Terjadi kesalahan",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
        }
        if (typeEntity.equals(TV_SHOW_TYPE)) {
            viewModel.detailTvShow.observe(this, { detailTvShow ->
                if (detailTvShow != null) {
                    when (detailTvShow.status) {
                        Status.LOADING -> binding?.progressBar?.visibility =
                            View.VISIBLE
                        Status.SUCCESS -> if (detailTvShow.data != null) {
                            binding?.progressBar?.visibility =
                                View.GONE
                            val state = detailTvShow.data.bookmarked
                            setBookmarkState(state)
                        }
                        Status.ERROR -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(
                                applicationContext,
                                "Terjadi kesalahan",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_bookmark) {
            if (typeEntity.equals(TV_SHOW_TYPE)) {
                viewModel.setBookmark(TV_SHOW_TYPE)
            }
            if (typeEntity.equals(MOVIE_TYPE)) {
                viewModel.setBookmark(MOVIE_TYPE)
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBookmarkState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_bookmark)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmarked_white_24dp)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_white_24dp)
        }
    }
}