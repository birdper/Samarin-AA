package org.birdper.movies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.birdper.movies.data.MovieRepository
import org.birdper.movies.presentation.state.MovieListUiState

class MovieListViewModel(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(MovieListUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            movieRepository.topMovies.collect { items ->
                _state.update {
                    it.copy(items = items)
                }
            }
        }
    }
}