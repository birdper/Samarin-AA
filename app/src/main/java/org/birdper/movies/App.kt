package org.birdper.movies

import android.app.Application
import org.birdper.movies.data.MovieRepository

class App : Application() {

    val movieRepository = MovieRepository()
}