package com.liarstudio.moviesearch.model.response;

import com.google.gson.annotations.SerializedName;
import com.liarstudio.moviesearch.domain.Movie
import com.liarstudio.moviesearch.model.response.base.BaseResponse

/**
 * Класс-обертка над обычным списком из фильмов, нужен для того, чтобы получать список
 * из JSON-ответа сервера.
 */
class MovieListResponse(
    @SerializedName("results") val items: List<MovieResponse>?
) : BaseResponse<List<Movie>> {

    override fun convert(): List<Movie> = items?.map(MovieResponse::convert) ?: emptyList()
}
