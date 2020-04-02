package com.liarstudio.moviesearch.ui.main

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.liarstudio.moviesearch.ui.detail.MovieDetailActivity
import com.liarstudio.moviesearch.R
import com.liarstudio.moviesearch.domain.Movie
import com.liarstudio.moviesearch.model.API_KEY
import com.liarstudio.moviesearch.model.BASE_URL
import com.liarstudio.moviesearch.model.api.MovieApi
import com.liarstudio.moviesearch.model.response.MovieListResponse
import com.liarstudio.moviesearch.ui.main.controller.MovieListController
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

class MainActivity : AppCompatActivity() {

    val adapter = EasyAdapter()
    val controller = MovieListController(onMovieClick = { openDetailsScreen(it) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movies_rv.adapter = adapter
        movies_rv.layoutManager = LinearLayoutManager(this)
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val movieApi = retrofit.create(MovieApi::class.java)
        val call = movieApi.discover(
            API_KEY,
            "RU"
        )

        call.enqueue(object : Callback<MovieListResponse> {
            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                showError()
            }

            override fun onResponse(
                call: Call<MovieListResponse>,
                response: Response<MovieListResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val list = response.body()?.convert() ?: emptyList()
                    showMovies(list)
                }
            }
        })


//        search_et.addTextChangedListener(createOnTextChangeListener()) //TODO поиск текста
    }

    private fun createOnTextChangeListener() = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            //ничего не делаем
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //ничего не делаем
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val text = s ?: return
            //TODO поиск текста
        }
    }

    private fun showError() {
        Toast.makeText(
            this,
            "Ошибка при загрузке",
            Toast.LENGTH_LONG
        ).show()
    }

    fun showMovies(movies: List<Movie>) {
        adapter.setItems(ItemList.create().addAll(movies, controller))
    }

    private fun openDetailsScreen(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.MOVIE_EXTRA, movie)
        startActivity(intent)
    }
}