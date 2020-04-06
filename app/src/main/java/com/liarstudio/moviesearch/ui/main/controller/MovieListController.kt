package com.liarstudio.moviesearch.ui.main.controller

import android.view.ViewGroup
import android.widget.TextView
import com.liarstudio.moviesearch.R
import com.liarstudio.moviesearch.entity.Movie
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class MovieListController(
    private val onMovieClick: (Movie) -> Unit
) : BindableItemController<Movie, MovieListController.Holder>() {

    override fun getItemId(data: Movie) = data.id.toString()

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    inner class Holder(parent: ViewGroup) : BindableViewHolder<Movie>(
        parent,
        R.layout.item_movie
    ) {
        override fun bind(data: Movie) {
            (itemView as TextView).text = data.title
            itemView.setOnClickListener { onMovieClick(data) }

        }
    }
}