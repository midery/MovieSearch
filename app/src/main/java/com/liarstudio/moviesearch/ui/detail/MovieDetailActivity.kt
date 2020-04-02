package com.liarstudio.moviesearch.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.liarstudio.moviesearch.R
import com.liarstudio.moviesearch.domain.Movie
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        showMovie(intent.getSerializableExtra(MOVIE_EXTRA) as Movie)
    }

    fun showSame(sameMovies: List<Movie>) {
        movie_same_name_1.text = sameMovies.getOrNull(0)?.title
        movie_same_name_2.text = sameMovies.getOrNull(1)?.title
        movie_same_name_3.text = sameMovies.getOrNull(2)?.title
    }

    private fun showMovie(movie: Movie) {
        movie_title_tv.text = movie.title

        Glide.with(movie_poster_iv)
            .load("https://image.tmdb.org/t/p/w370_and_h556_bestv2" + movie.posterUrl)
            .into(movie_poster_iv)

        findSame(movie)
    }

    private fun findSame(movie: Movie) {
        //TODO поиск похожих фильмов
    }

    private fun showError() {
        Toast.makeText(this, "Ошибка при загрузке", Toast.LENGTH_SHORT).show()
    }

    companion object {
        val MOVIE_EXTRA = "movie"
    }
}