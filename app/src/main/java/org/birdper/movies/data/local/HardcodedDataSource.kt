package org.birdper.movies.data.local

import org.birdper.movies.domain.model.Country
import org.birdper.movies.domain.model.Genre
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
        return _data.find { it.kinopoiskId == movieId }
    }

    fun updateMovie(movieId: Int, updatedMovie: MovieItem) {
        val index = _data.indexOfFirst { it.kinopoiskId == movieId }
        if (index == -1) return

        _data[index] = updatedMovie.copy()
    }

    private fun populateData() {
        val genres = listOf(Genre("фантастика"), Genre("фэнтэзи"), Genre("боевик"), Genre("триллер"), Genre("комедия"), Genre("хоррор"))
        val countries = listOf(Country("USA"), Country("Canada"), Country("France"))

        val years = 1960..2024

        repeat(30) {
            val todoItem = MovieItem(
                kinopoiskId = idIncrement,
                nameRu = "Название $idIncrement",
                description = "Этот фильм – Название $idIncrement очень хороший, мой рекомендасьон",
                isFavorite = Random.nextBoolean(),
                genres = genres.shuffled(),
                countries = countries.shuffled(),
                year = years.random().toString(),
                posterPreviewUrl = "",
                posterUrl = "",

            )
            idIncrement++

            _data.add(todoItem)
        }
    }
}