package com.liarstudio.moviesearch.model.interactor

import androidx.lifecycle.LiveData
import com.liarstudio.moviesearch.entity.Movie
import com.liarstudio.moviesearch.model.repo.MovieListResult
import com.liarstudio.moviesearch.model.repo.MovieRepo
import com.liarstudio.moviesearch.model.repo.MovieRepositoryProvider

class MovieInteractor(
    private val repo: MovieRepo
) {

    fun discover(): LiveData<MovieListResult> = repo.discover()

    fun search(query: String): LiveData<MovieListResult> = repo.search(query)

    fun findSame(movie: Movie): LiveData<MovieListResult> = repo.findSame(movie)
}