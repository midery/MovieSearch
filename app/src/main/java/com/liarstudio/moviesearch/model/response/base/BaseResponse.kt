package com.liarstudio.moviesearch.model.response.base

interface BaseResponse<T> {

    fun convert(): T
}