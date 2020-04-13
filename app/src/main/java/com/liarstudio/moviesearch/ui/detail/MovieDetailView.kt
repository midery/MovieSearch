package com.liarstudio.moviesearch.ui.detail

import com.liarstudio.moviesearch.domain.Movie

interface MovieDetailView {
    fun showSame(movies: List<Movie>)

    fun showError()
}