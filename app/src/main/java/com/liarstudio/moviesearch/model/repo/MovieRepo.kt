package com.liarstudio.moviesearch.model.repo

import com.liarstudio.moviesearch.model.API_KEY
import com.liarstudio.moviesearch.model.BASE_URL
import com.liarstudio.moviesearch.model.api.MovieApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepo {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val movieApi = retrofit.create(MovieApi::class.java)


    fun discover() = movieApi.discover(API_KEY, "RU")

    fun search(query: String) = movieApi.search(API_KEY, "RU", query)

}