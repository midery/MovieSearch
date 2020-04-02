package com.liarstudio.moviesearch

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.liarstudio.moviesearch.repository.MovieRepository
import com.liarstudio.moviesearch.repository.MovieRepositoryProvider
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
    val controller = MovieListController {
        startActivity(
            Intent(
                this,
                MovieDetailActivity::class.java
            ).putExtra(MovieDetailActivity.MOVIE_EXTRA, it)
        )
    }

    val viewModel = MainVM()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movies_rv.adapter = adapter
        movies_rv.layoutManager = LinearLayoutManager(this)

        viewModel.movies.observe(this, Observer { result ->
            when {
                result.isSuccess -> showMovies(result.getOrThrow())
                result.isFailure -> showError()
            }
        })

        search_et.addTextChangedListener(createOnTextChangeListener())
    }

    private fun showError() {
        Toast.makeText(
            this,
            "Ошибка при загрузке",
            Toast.LENGTH_LONG
        ).show()
    }

    fun createOnTextChangeListener() = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val text = s ?: return
            viewModel.searchText.value = text.toString()
        }
    }

    fun showMovies(movies: List<Movie>) {
        adapter.setItems(ItemList.create().addAll(movies, controller))

    }
}