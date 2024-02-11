package org.birdper.movies.presentation.adapter

import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import org.birdper.movies.databinding.MovieItemBinding
import org.birdper.movies.presentation.model.MovieItem

class MovieItemViewHolder(
    val binding: MovieItemBinding,
    private val onClickItem: (movieId: Int) -> Unit,
    private val onLongClickItem: (movieId: Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var movieItem: MovieItem

    init {
        binding.root.setOnClickListener {
            if (adapterPosition == RecyclerView.NO_POSITION)
                return@setOnClickListener
            onClickItem(movieItem.id)
        }
        binding.root.setOnLongClickListener {
            if (adapterPosition == RecyclerView.NO_POSITION)
                return@setOnLongClickListener false
            onLongClickItem(movieItem.id)
            true
        }
    }

    fun onBind(item: MovieItem) {
        movieItem = item
        with(binding) {
            tvMovieName.text = item.name
            tvGenreAndYear.text = item.genresAndYear
            imgFavorite.isGone = !item.isFavorite
        }
    }
}
