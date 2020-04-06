package com.liarstudio.moviesearch.ui.main

import com.liarstudio.moviesearch.model.repo.MovieRepositoryProvider

class MainPresenter(
    private val view: MainActivity
) {

    private val repo = MovieRepositoryProvider.movieRepository

    fun onCreate() {
        repo.discover({ view.showMovies(it) }, { view.showError() })
    }

    fun onQueryChanged(query: String) {
        repo.search(query, { view.showMovies(it) }, { view.showError() })
    }
}