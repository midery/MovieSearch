package com.liarstudio.moviesearch.model.repo

object MovieRepositoryProvider {
    val movieRepository by lazy { MovieRepo() }
}