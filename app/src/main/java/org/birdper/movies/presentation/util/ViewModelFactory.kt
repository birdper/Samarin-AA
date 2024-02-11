package org.birdper.movies.presentation.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import org.birdper.movies.App
import org.birdper.movies.presentation.viewmodel.MovieListViewModel
import java.lang.IllegalStateException

typealias ViewModelCreator = (App) -> ViewModel?

class ViewModelFactory(
    val app: App,
    val viewModelCreator: ViewModelCreator = { null },
) : androidx.lifecycle.ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {

            MovieListViewModel::class.java -> {
                MovieListViewModel(app.movieRepository)
            }

            else -> {
                viewModelCreator(app) ?: throw IllegalStateException("Unknown ViewModel class")
            }
        }
        return viewModel as T
    }
}


fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext as App)

inline fun <reified VM : ViewModel> Fragment.viewModelCreator(
    noinline creator: ViewModelCreator,
): Lazy<VM> {
    return viewModels { ViewModelFactory(requireContext().applicationContext as App, creator) }
}