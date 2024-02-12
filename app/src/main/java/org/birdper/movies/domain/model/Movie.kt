package org.birdper.movies.domain.model

data class Movie(
    val kinopoiskId: Int,
    val imdbId: String = "",
    val nameEn: String = "",
    val nameRu: String,
    val nameOriginal: String = "",
    val year: Int,
    val description: String = "",
    val genres: List<Genre>,
    val countries: List<Country>,
    val posterUrl: String,
    val posterPreviewUrl: String,
    val ratingImdb: Double = 0.0,
    val ratingKinopoisk: Double = 0.0,
    val type: String,
)
