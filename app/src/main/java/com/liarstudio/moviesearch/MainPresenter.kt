package com.liarstudio.moviesearch

import com.liarstudio.moviesearch.repository.MovieRepository
import com.liarstudio.moviesearch.repository.MovieRepositoryCopy

class MainPresenter(
        val view: MainActivity
    ) {
        val repo = MovieRepositoryCopy()

        fun onCreate() {
            getMovies("")
        }

        fun onQueryChanged(query: String) {
            getMovies(query)
        }

        private fun getMovies(query: String) {
            if (query.isEmpty()) {
//                repo.discover({ view.showMovies(it) })
            } else {
//                repo.search(query, { view.showMovies(it) })
            }
        }
    }