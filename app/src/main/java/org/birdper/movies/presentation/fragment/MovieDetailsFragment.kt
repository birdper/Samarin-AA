package org.birdper.movies.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch
import org.birdper.movies.R
import org.birdper.movies.databinding.FragmentMovieDetailsBinding
import org.birdper.movies.presentation.state.MovieDetailsUiState
import org.birdper.movies.presentation.util.viewModelCreator
import org.birdper.movies.presentation.viewmodel.MovieDetailsViewModel

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentMovieDetailsBinding not found")

    private val args by navArgs<MovieDetailsFragmentArgs>()
    private val viewModel: MovieDetailsViewModel by viewModelCreator { app ->
        MovieDetailsViewModel(app.movieRepository, args.movieId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieDetailsBinding.bind(view)

        setupClickListeners()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collect { state ->
                    setupView(state)
                }
            }
        }
    }

    private fun setupView(uiState: MovieDetailsUiState) {
        binding.tvMovieName.text = uiState.name
        binding.tvMovieDescription.text = uiState.description
    }

    private fun setupClickListeners() {
        binding.tbMovieDetails.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}