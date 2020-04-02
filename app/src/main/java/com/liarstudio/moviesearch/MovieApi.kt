package com.liarstudio.moviesearch

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {

    /**
     * discover - отвечает за загрузку фильмов без строки поиска
     * @param key - API-ключ пользователя
     * @param language - Язык выдачи запроса
     * @return возвращает список фильмов
     */
    @GET("discover/movie")
    fun discover(@Query("api_key") key: String, @Query("language") language: String): Call<MovieList>

    /**
     * search - отвечает за загрузку фильмов по заданному поисковому запросу
     * @param key - API-ключ пользователя
     * @param language - язык выдачи запроса
     * @param query - поисковый запрос
     * @return возвращает список фильмов
     */
    @GET("search/movie")
    fun search(@Query("api_key") key: String, @Query("language") language: String, @Query("query") query: String): Call<MovieList>

}
