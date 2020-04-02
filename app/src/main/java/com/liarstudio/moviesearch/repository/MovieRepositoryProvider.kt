package com.liarstudio.moviesearch.repository

object MovieRepositoryProvider {
    val movieRepository by lazy { MovieRepository() }
}