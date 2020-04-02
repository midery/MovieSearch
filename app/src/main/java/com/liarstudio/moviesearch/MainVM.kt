package com.liarstudio.moviesearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.liarstudio.moviesearch.repository.MovieListResult
import com.liarstudio.moviesearch.repository.MovieRepositoryProvider

class MainVM : ViewModel() {

    private val repo = MovieRepositoryProvider.movieRepository

    val searchText = MutableLiveData<String>().apply { value = "" }

    var movies: LiveData<MovieListResult> =
        Transformations.switchMap(searchText) { query ->
            if (query.isEmpty()) {
                repo.discover()
            } else {
                repo.search(query)
            }
        }
}