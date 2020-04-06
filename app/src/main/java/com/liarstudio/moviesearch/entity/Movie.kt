package com.liarstudio.moviesearch.entity

import java.io.Serializable

data class Movie(
    val id: Int = 0,
    val title: String,
    val description: String = "",
    val releaseDate: String? = "",
    val posterUrl: String? = ""
): Serializable