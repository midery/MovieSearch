package com.liarstudio.moviesearch.ui.detail

import androidx.lifecycle.*
import com.liarstudio.moviesearch.entity.Movie
import com.liarstudio.moviesearch.model.interactor.MovieInteractor
import com.liarstudio.moviesearch.model.interactor.MovieInteractorProvider
import com.liarstudio.moviesearch.model.repo.MovieListResult

class MovieListVM : ViewModel() {

    private val movieInteractor = MovieInteractorProvider.movieInteractor

    val sameMovies: MediatorLiveData<MovieListResult> = MediatorLiveData()

    fun findSame(movie: Movie) {
        val moviesLiveData = movieInteractor.findSame(movie)
        sameMovies.addSource(moviesLiveData, sameMovies::setValue)
    }
}
