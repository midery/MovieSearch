package com.liarstudio.moviesearch.ui.detail

import androidx.lifecycle.*
import com.liarstudio.moviesearch.domain.Movie
import com.liarstudio.moviesearch.model.repo.MovieListResult
import com.liarstudio.moviesearch.model.repo.MovieRepositoryProvider

class MovieListVM : ViewModel() {

    private val repo = MovieRepositoryProvider.movieRepository

    val sameMovies: MediatorLiveData<MovieListResult> = MediatorLiveData()

    fun findSame(movie: Movie) {
        val moviesLiveData = repo.findSame(movie)
        sameMovies.addSource(moviesLiveData, sameMovies::setValue)
    }
}
