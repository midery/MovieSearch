package com.liarstudio.moviesearch.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.liarstudio.moviesearch.model.interactor.MovieInteractor
import com.liarstudio.moviesearch.model.interactor.MovieInteractorProvider
import com.liarstudio.moviesearch.model.repo.MovieListResult

class MovieListVM : ViewModel() {

    private val movieInteractor = MovieInteractorProvider.movieInteractor

    val searchText = MutableLiveData<String>()

    val movies: LiveData<MovieListResult> =
        Transformations.switchMap(searchText) { query ->
            if (query.isEmpty()) {
                movieInteractor.discover()
            } else {
                movieInteractor.search(query)
            }
        }
}