package org.birdper.movies.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val items: List<Item>,
    val total: Int,
    val totalPages: Int
)