package org.birdper.movies.presentation.state

import org.birdper.movies.presentation.model.MovieItem

data class MovieListUiState(
    val items: List<MovieItem> = emptyList()
)