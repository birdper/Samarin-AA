package org.birdper.movies

import android.app.Application
import org.birdper.movies.data.MovieRepository
import org.birdper.movies.data.remote.MoviesRemoteDataSource


class App : Application() {

    private val remoteDataSource = MoviesRemoteDataSource()
    val movieRepository = MovieRepository(remoteDataSource = remoteDataSource)
}