package com.liarstudio.moviesearch.ui.main

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.liarstudio.moviesearch.ui.detail.MovieDetailActivity
import com.liarstudio.moviesearch.R
import com.liarstudio.moviesearch.entity.Movie
import com.liarstudio.moviesearch.ui.main.controller.MovieListController
import kotlinx.android.synthetic.main.activity_main.*
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

class MovieListActivity : AppCompatActivity() {

    val adapter = EasyAdapter()
    val controller = MovieListController(onMovieClick = { openDetailsScreen(it) })

    lateinit var vm: MovieListVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movies_rv.adapter = adapter
        movies_rv.layoutManager = LinearLayoutManager(this)
        search_et.addTextChangedListener(createOnTextChangeListener())

        vm = ViewModelProvider(this).get(MovieListVM::class.java)

        vm.movies.observe(this, Observer { result ->
            when {
                result.isFailure -> showError()
                result.isSuccess -> showMovies(result.getOrThrow())
            }
        })

        val isFirstCreate = savedInstanceState == null
        if (isFirstCreate) {
            vm.searchText.value = ""
        }
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
            vm.searchText.value = text.toString()
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