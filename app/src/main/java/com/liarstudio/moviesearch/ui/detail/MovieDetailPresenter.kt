package com.liarstudio.moviesearch.ui.detail

import com.liarstudio.moviesearch.domain.Movie
import com.liarstudio.moviesearch.model.repo.MovieRepositoryProvider

class MovieDetailPresenter(
    private val view: MovieDetailView
) {

    private val repo = MovieRepositoryProvider.movieRepository

    fun findSame(movie: Movie) {
        view.showSame(listOf(Movie(title = "hello")))
    }
}