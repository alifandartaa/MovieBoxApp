package com.example.movieboxapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.movieboxapp.data.source.entity.DetailEntity
import com.example.movieboxapp.repository.MovieTvRepository
import com.example.movieboxapp.vo.Resource

class DetailViewModel(private val movieTvRepository: MovieTvRepository) : ViewModel() {

    companion object {
        const val MOVIE_TYPE = "MOVIE_TYPE"
        const val TV_SHOW_TYPE = "TV_SHOW_TYPE"
    }

    //    private var id: Int = 0
    val detailId = MutableLiveData<Int>()

    fun selectId(id: Int) {
        this.detailId.value = id
    }

//    fun getDetailMovie(): LiveData<Resource<DetailEntity>>? = id?.let { movieTvRepository.getDetailMovie(id) }

//    fun getDetailTvshow(): LiveData<Resource<DetailEntity>>? =
//        id?.let { movieTvRepository.getDetailTvshow(it) }

    var detailMovie: LiveData<Resource<DetailEntity>> = Transformations.switchMap(detailId) {
        movieTvRepository.getDetailMovie(it)
    }

    var detailTvShow: LiveData<Resource<DetailEntity>> = Transformations.switchMap(detailId) {
        movieTvRepository.getDetailTvshow(it)
    }

    fun setBookmark(type: String) {
        if (type == TV_SHOW_TYPE) {
            val detailResource = detailTvShow.value
            if (detailResource != null) {
                val detailEntity = detailResource.data
                val newState = !detailEntity!!.bookmarked
                movieTvRepository.setBookmark(detailEntity, newState)
            }
        }
        if (type == MOVIE_TYPE) {
            val detailResource = detailMovie.value
            if (detailResource != null) {
                val detailEntity = detailResource.data
                val newState = !detailEntity!!.bookmarked
                movieTvRepository.setBookmark(detailEntity, newState)
            }
        }
    }
}