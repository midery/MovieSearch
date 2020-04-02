package com.liarstudio.moviesearch.model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalStateException

class RetrofitCallback<T>(
    val onSuccess: (T) -> Unit,
    val onError: (Throwable) -> Unit
) : Callback<T> {

    override fun onFailure(call: Call<T>, t: Throwable) {
        //todo on error
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        //TODO check response body
    }
}