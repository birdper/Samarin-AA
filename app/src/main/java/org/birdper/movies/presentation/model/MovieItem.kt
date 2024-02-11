package org.birdper.movies.presentation.model

data class MovieItem(
    val id: Int,
    val name: String,
    val year: String,
    val genre: String,
    val description: String,
    val posterPreviewUrl: String,
    val isFavorite: Boolean = false,
) {
    val genresAndYear = "$genre ($year)"
}