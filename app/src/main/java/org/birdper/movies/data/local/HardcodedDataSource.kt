package org.birdper.movies.data.local

import org.birdper.movies.presentation.model.MovieItem
import kotlin.random.Random

class HardcodedDataSource {

    private var idIncrement = 1

    private val _data = mutableListOf<MovieItem>()
    val data get() = _data.toList()

    init {
        populateData()
    }

    fun findMovieById(movieId: Int): MovieItem? {
        return _data.find { it.id == movieId }
    }

    fun updateMovie(movieId: Int, updatedMovie: MovieItem) {
        val index = _data.indexOfFirst { it.id == movieId }
        if (index == -1) return

        _data[index] = updatedMovie.copy()
    }

    private fun populateData() {
        val genres = listOf("фантастика", "фэнтэзи", "боевик", "триллер", "комедия", "хоррор")

        val years = 1960..2024

        repeat(30) {
            val todoItem = MovieItem(
                id = idIncrement,
                name = "Название $idIncrement",
                description = "Этот фильм – Название $idIncrement очень хороший, мой рекомендасьон",
                isFavorite = Random.nextBoolean(),
                genre = genres[Random.nextInt(until = genres.size)],
                year = years.random().toString(),
                posterPreviewUrl = "",
            )
            idIncrement++

            _data.add(todoItem)
        }
    }
}