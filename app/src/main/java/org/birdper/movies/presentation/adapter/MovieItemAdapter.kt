package org.birdper.movies.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import org.birdper.movies.databinding.MovieItemBinding
import org.birdper.movies.presentation.model.MovieItem

class MovieItemAdapter(
    private val onClickItem: (movieId: Int) -> Unit,
    private val onLongClickItem: (movieId: Int) -> Unit,
) : ListAdapter<MovieItem, MovieItemViewHolder>(MovieItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)

        return MovieItemViewHolder(
            binding = binding,
            onClickItem = onClickItem,
            onLongClickItem = onLongClickItem
        )
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


    object MovieItemDiffCallback : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
            oldItem.kinopoiskId == newItem.kinopoiskId

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
            oldItem == newItem
    }
}