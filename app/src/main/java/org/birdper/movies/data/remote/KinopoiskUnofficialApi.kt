package org.birdper.movies.data.remote

import org.birdper.movies.data.remote.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KinopoiskUnofficialApi {

    @GET("collections")
    suspend fun getTopMovies(
        @Query("type") collection: String,
        @Query("page") page: Int,
    ): Response<MovieResponse>
}

