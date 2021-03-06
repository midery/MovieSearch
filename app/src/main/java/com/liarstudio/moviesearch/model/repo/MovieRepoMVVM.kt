package com.liarstudio.moviesearch.model.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.liarstudio.moviesearch.domain.Movie
import com.liarstudio.moviesearch.model.API_KEY
import com.liarstudio.moviesearch.model.BASE_URL
import com.liarstudio.moviesearch.model.RetrofitCallback
import com.liarstudio.moviesearch.model.api.MovieApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

typealias MovieListResult = Result<List<Movie>>

class MovieRepoMVVM {

    companion object {
        const val LANG_RU = "RU"
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val movieApi = retrofit.create(MovieApi::class.java)

    fun discover(): LiveData<MovieListResult> {
        val movies = MutableLiveData<Result<List<Movie>>>()
        movieApi
            .discover(
                API_KEY,
                LANG_RU
            )
            .enqueue(
                RetrofitCallback(
                    { data -> movies.value = Result.success(data.convert()) },
                    { error -> movies.value = Result.failure(error) }
                )
            )
        return movies
    }

    fun search(title: String): LiveData<MovieListResult> {
        val movies = MutableLiveData<MovieListResult>()
        movieApi
            .search(
                API_KEY,
                LANG_RU, title
            )
            .enqueue(
                RetrofitCallback(
                    { data -> movies.value = Result.success(data.convert()) },
                    { error -> movies.value = Result.failure(error) })
            )
        return movies
    }


    fun findSame(
        movie: Movie,
        onSuccess: (List<Movie>) -> Unit,
        onError: (Throwable) -> Unit = { }
    ) = movieApi
        .search(
            API_KEY,
            LANG_RU, movie.title.split(' ')[0]
        )
        .enqueue(
            RetrofitCallback(
                { onSuccess(it.convert()) },
                onError
            )
        )
}