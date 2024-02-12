package org.birdper.movies.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import org.birdper.movies.data.local.HardcodedDataSource
import org.birdper.movies.data.remote.MoviesRemoteDataSource
import org.birdper.movies.presentation.model.MovieItem

class MovieRepository(
    private val hardcodedDataSource: HardcodedDataSource = HardcodedDataSource(),
    private val remoteDataSource: MoviesRemoteDataSource,
    private val defaultContext: CoroutineDispatcher = Dispatchers.IO,
) {

    suspend fun getTopMovies(page: Int) = withContext(defaultContext) {
        remoteDataSource.getTopMovies(page)
    }

    val favoriteMovies by lazy { flowOf(hardcodedDataSource.data.filter { it.isFavorite }) }

    suspend fun findMovieById(movieId: Int): MovieItem? =
        hardcodedDataSource.findMovieById(movieId)

    suspend fun updateMovie(movieId: Int, updatedMovie: MovieItem) {
        hardcodedDataSource.updateMovie(movieId, updatedMovie)
    }

    companion object {
        const val TOP_POPULAR_MOVIES = "TOP_POPULAR_MOVIES"
        const val TOP_250 = "TOP_250_MOVIES"
    }
}