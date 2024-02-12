package org.birdper.movies.presentation.model

import org.birdper.movies.domain.model.Country
import org.birdper.movies.domain.model.Genre

data class MovieItem(
    val kinopoiskId: Int,
    val nameRu: String,
    val year: String,
    val genres: List<Genre>,
    val countries: List<Country>,
    val description: String = "",
    val posterUrl: String,
    val posterPreviewUrl: String,
    val isFavorite: Boolean = false,
) {
    val genresAndYear = "${genres[0].genre} ($year)"
}