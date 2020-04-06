package com.liarstudio.moviesearch.model.repo

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCallback<T>(
    val onSuccess: (T) -> Unit,
    val onError: (Throwable) -> Unit
) : Callback<T> {

    override fun onFailure(call: Call<T>, t: Throwable) {
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
    }
}