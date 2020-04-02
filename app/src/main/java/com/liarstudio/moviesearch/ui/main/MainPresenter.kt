package com.liarstudio.moviesearch.ui.main

import com.liarstudio.moviesearch.model.repo.MovieRepoMVP

class MainPresenter(
        val view: MainActivity
    ) {
        val repo = MovieRepoMVP()

        fun onCreate() {
            getMovies("")
        }

        fun onQueryChanged(query: String) {
            getMovies(query)
        }

        private fun getMovies(query: String) {
            if (query.isEmpty()) {
                repo.discover({ view.showMovies(it) })
            } else {
                repo.search(query, { view.showMovies(it) })
            }
        }
    }