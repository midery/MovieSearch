package com.liarstudio.moviesearch.model.response

import com.google.gson.annotations.SerializedName
import com.liarstudio.moviesearch.domain.Movie
import com.liarstudio.moviesearch.model.response.base.BaseResponse

class MovieResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("overview") val description: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("poster_path") val posterUrl: String?
) : BaseResponse<Movie> {

    override fun convert(): Movie = Movie(
        id ?: 0,
        title ?: "",
        description ?: "",
        releaseDate ?: "",
        posterUrl ?: ""
    )
}