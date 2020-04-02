package com.liarstudio.moviesearch.model.repo

import com.liarstudio.moviesearch.domain.Movie
import com.liarstudio.moviesearch.model.API_KEY
import com.liarstudio.moviesearch.model.BASE_URL
import com.liarstudio.moviesearch.model.RetrofitCallback
import com.liarstudio.moviesearch.model.api.MovieApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepoMVP {

    companion object {
        const val LANG_RU = "RU"
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val movieApi = retrofit.create(MovieApi::class.java)

    fun discover(
        onSuccess: (List<Movie>) -> Unit,
        onError: (Throwable) -> Unit = { }
    ) =
        movieApi
            .discover(
                API_KEY,
                LANG_RU
            )
            .enqueue(
                RetrofitCallback(
                    { onSuccess(it.convert()) },
                    onError
                )
            )

    fun search(
        title: String,
        onSuccess: (List<Movie>) -> Unit,
        onError: (Throwable) -> Unit = { }
    ) =
        movieApi
            .search(
                API_KEY,
                LANG_RU, title
            )
            .enqueue(
                RetrofitCallback(
                    { onSuccess(it.convert()) },
                    onError
                )
            )


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