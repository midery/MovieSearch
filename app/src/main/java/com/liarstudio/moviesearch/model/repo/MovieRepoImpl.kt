package com.liarstudio.moviesearch.model.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.liarstudio.moviesearch.entity.Movie
import com.liarstudio.moviesearch.model.API_KEY
import com.liarstudio.moviesearch.model.BASE_URL
import com.liarstudio.moviesearch.model.RetrofitCallback
import com.liarstudio.moviesearch.model.web.MovieApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepoImpl : MovieRepo {

    companion object {
        const val LANG_RU = "RU"
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val movieApi = retrofit.create(MovieApi::class.java)

    override fun discover(): LiveData<MovieListResult> {
        val movies = MutableLiveData<MovieListResult>()

        movieApi
            .discover(API_KEY, LANG_RU)
            .enqueue(
                RetrofitCallback(
                    { data -> movies.value = Result.success(data.convert()) },
                    { error -> movies.value = Result.failure(error) }
                )
            )

        return movies
    }

    override fun search(query: String): LiveData<MovieListResult> {
        val movies = MutableLiveData<MovieListResult>()
        movieApi
            .search(API_KEY, LANG_RU, query)
            .enqueue(
                RetrofitCallback(
                    { data ->
                        movies.value = Result.success(data.convert())
                    },
                    { error ->
                        movies.value = Result.failure(error)
                    })
            )
        return movies
    }


    override fun findSame(movie: Movie): LiveData<MovieListResult> {
        val movies = MutableLiveData<MovieListResult>()

        movieApi
            .search(API_KEY, LANG_RU, findSamePredicate(movie))
            .enqueue(
                RetrofitCallback(
                    { data -> movies.value = Result.success(data.convert()) },
                    { error -> movies.value = Result.failure(error) }
                )
            )
        return movies
    }

    private fun findSamePredicate(movie: Movie): String {
        return movie.title.split(' ')[0]
    }

}