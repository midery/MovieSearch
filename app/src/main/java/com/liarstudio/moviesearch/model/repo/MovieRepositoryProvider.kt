package com.liarstudio.moviesearch.model.repo

object MovieRepositoryProvider {
    val movieRepository: MovieRepo by lazy { MovieRepoImpl() }
}