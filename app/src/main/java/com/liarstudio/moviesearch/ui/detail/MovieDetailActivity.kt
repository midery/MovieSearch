package com.liarstudio.moviesearch.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.liarstudio.moviesearch.R
import com.liarstudio.moviesearch.domain.Movie
import com.liarstudio.moviesearch.model.repo.MovieRepositoryProvider
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movie = intent.getSerializableExtra(MOVIE_EXTRA) as Movie

        Glide.with(movie_poster_iv)
            .load("https://image.tmdb.org/t/p/w370_and_h556_bestv2" + movie.posterUrl)
            .into(movie_poster_iv)

        movie_title_tv.text = movie.title

        val repository = MovieRepositoryProvider.movieRepository
        repository.findSame(movie, { list ->
            movie_same_name_1.text = list[0].title
            movie_same_name_2.text = list[1].title
            movie_same_name_3.text = list[2].title
        })
    }

    companion object {
        val MOVIE_EXTRA = "movie"
    }
}