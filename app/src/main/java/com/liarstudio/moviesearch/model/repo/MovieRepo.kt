package com.liarstudio.moviesearch.model.repo

import androidx.lifecycle.LiveData
import com.liarstudio.moviesearch.entity.Movie

typealias MovieListResult = Result<List<Movie>>


interface MovieRepo {

    fun search(query: String): LiveData<MovieListResult>

    fun discover(): LiveData<MovieListResult>

    fun findSame(movie: Movie): LiveData<MovieListResult>
}