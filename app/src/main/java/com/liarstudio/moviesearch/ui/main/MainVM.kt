package com.liarstudio.moviesearch.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.liarstudio.moviesearch.model.repo.MovieListResult
import com.liarstudio.moviesearch.model.repo.MovieRepositoryProvider

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