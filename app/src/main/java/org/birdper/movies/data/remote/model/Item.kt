package org.birdper.movies.data.remote.model

import kotlinx.serialization.Serializable
import org.birdper.movies.domain.model.Country
import org.birdper.movies.domain.model.Genre

@Serializable
data class Item(
    val countries: List<Country>,
    val genres: List<Genre>,
    val imdbId: String? = "",
    val kinopoiskId: Int,
    val nameEn: String? = "",
    val nameOriginal: String? = "",
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val ratingImdb: Double,
    val ratingKinopoisk: Double,
    val type: String,
    val year: Int
)