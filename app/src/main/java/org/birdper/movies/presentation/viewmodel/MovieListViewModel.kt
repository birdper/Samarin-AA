package org.birdper.movies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.birdper.movies.Error
import org.birdper.movies.Idle
import org.birdper.movies.Loading
import org.birdper.movies.data.MovieRepository
import org.birdper.movies.data.remote.model.Item
import org.birdper.movies.domain.model.Movie
import org.birdper.movies.presentation.model.MovieItem
import org.birdper.movies.presentation.state.MovieListUiState

class MovieListViewModel(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(MovieListUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val data = movieRepository.getTopMovies(1)
            when(data.status) {
                Loading -> { }
                is Error -> {

                }
                Idle -> {
                    data.value?.let {
                        _state.update { uiState ->
                            uiState.copy(items = it.items.map { it.toMovieItem() })
                        }
                    }
                }
            }

        }
    }

    private fun Item.toMovie(): Movie {
        return Movie(
            kinopoiskId = kinopoiskId,
            imdbId = imdbId ?: "",
            nameRu = nameRu,
            nameEn = nameEn ?: "",
            nameOriginal = nameOriginal ?: "",
            posterPreviewUrl = posterUrlPreview,
            posterUrl = posterUrl,
            countries = countries,
            genres = genres,
            ratingImdb = ratingImdb,
            ratingKinopoisk = ratingKinopoisk,
            type = type,
            year = year,
        )
    }


    private fun Item.toMovieItem(): MovieItem {
        return MovieItem(
            kinopoiskId = kinopoiskId,
            nameRu = nameRu,
            posterPreviewUrl = posterUrlPreview,
            posterUrl = posterUrl,
            countries = countries,
            genres = genres,
            year = year.toString(),
        )
    }
}