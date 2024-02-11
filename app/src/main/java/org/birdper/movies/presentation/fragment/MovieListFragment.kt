package org.birdper.movies.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.birdper.movies.R
import org.birdper.movies.databinding.FragmentMovieListBinding
import org.birdper.movies.presentation.adapter.VerticalMarginDecoration
import org.birdper.movies.presentation.adapter.MovieItemAdapter
import org.birdper.movies.presentation.state.MovieListUiState
import org.birdper.movies.presentation.util.factory
import org.birdper.movies.presentation.viewmodel.MovieListViewModel

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private var _binding: FragmentMovieListBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentMovieListBinding not found")

    private val viewModel: MovieListViewModel by viewModels { factory() }

    private lateinit var adapter: MovieItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieListBinding.bind(view)

        setupRecyclerView()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collect { state ->
                    setupView(state)
                }
            }
        }
    }

    private fun setupView(state: MovieListUiState) {
        adapter.submitList(state.items)
    }

    private fun setupRecyclerView() {
        adapter = MovieItemAdapter(::onClickItem, ::onLongClickItem)

        binding.rvMovies.adapter = adapter
        binding.rvMovies.addItemDecoration(VerticalMarginDecoration(top = 16, inner = 16, bottom = 32))
    }

    private fun setupClickListeners() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.tab_popular -> {

                    true
                }

                R.id.tab_favorite -> {

                    true
                }

                else -> false
            }
        }
    }

    private fun onClickItem(movieId: Int) {
        findNavController().navigate(
            MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(
                movieId
            )
        )
    }

    private fun onLongClickItem(movieId: Int) {
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}