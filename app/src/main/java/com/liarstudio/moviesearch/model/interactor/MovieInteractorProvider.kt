package com.liarstudio.moviesearch.model.interactor

import com.liarstudio.moviesearch.model.repo.MovieRepositoryProvider

object MovieInteractorProvider {
    val movieInteractor: MovieInteractor by lazy { MovieInteractor(MovieRepositoryProvider.movieRepository) }
}