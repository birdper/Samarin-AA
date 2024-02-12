package org.birdper.movies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.birdper.movies.data.MovieRepository
import org.birdper.movies.presentation.state.MovieDetailsUiState

class MovieDetailsViewModel(
    private val movieRepository: MovieRepository,
    private val movieId: Int,
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailsUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            movieRepository.findMovieById(movieId)?.let { movieItem ->
                _state.update { state ->
                    state.copy(
                        id = movieId,
                        name = movieItem.nameRu,
                    )
                }
            }
        }
    }

}