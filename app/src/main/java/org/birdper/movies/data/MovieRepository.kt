package org.birdper.movies.data

import kotlinx.coroutines.flow.flowOf
import org.birdper.movies.data.local.HardcodedDataSource
import org.birdper.movies.presentation.model.MovieItem

class MovieRepository(
    private val hardcodedDataSource: HardcodedDataSource = HardcodedDataSource(),
) {

    val topMovies = flowOf(hardcodedDataSource.data)

    val favoriteMovies by lazy { flowOf(hardcodedDataSource.data.filter { it.isFavorite }) }

    suspend fun findMovieById(movieId: Int): MovieItem? =
        hardcodedDataSource.findMovieById(movieId)

    suspend fun updateMovie(movieId: Int, updatedMovie: MovieItem) {
        hardcodedDataSource.updateMovie(movieId, updatedMovie)
    }
}