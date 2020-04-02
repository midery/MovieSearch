package com.liarstudio.moviesearch.model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCallback<T>(
    val onSuccess: (T) -> Unit,
    val onError: (Throwable) -> Unit
) : Callback<T> {
    override fun onFailure(call: Call<T>, t: Throwable) {
        onError(t)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onSuccess(response.body() ?: return)
    }
}