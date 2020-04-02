package com.liarstudio.moviesearch.repository

import com.liarstudio.moviesearch.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepositoryCopy {

    companion object {
        const val LANG_RU = "RU"
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val movieApi = retrofit.create(MovieApi::class.java)

    fun discover(
        onSuccess: (MovieList) -> Unit,
        onError: (Throwable) -> Unit = { }
    ) =
        movieApi
            .discover(API_KEY, LANG_RU)
            .enqueue(RetrofitCallback(onSuccess, onError))

    fun search(
        title: String,
        onSuccess: (MovieList) -> Unit,
        onError: (Throwable) -> Unit = { }
    ) =
        movieApi
            .search(API_KEY, LANG_RU, title)
            .enqueue(RetrofitCallback(onSuccess, onError))


    fun findSame(
        movie: Movie,
        onSuccess: (MovieList) -> Unit,
        onError: (Throwable) -> Unit = { }
    ) = movieApi
        .search(API_KEY, LANG_RU, movie.title.split(' ')[0])
        .enqueue(RetrofitCallback(onSuccess, onError))
}