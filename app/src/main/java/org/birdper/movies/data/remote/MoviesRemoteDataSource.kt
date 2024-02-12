package org.birdper.movies.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.birdper.movies.ReloadableData
import org.birdper.movies.data.remote.model.MovieResponse
import retrofit2.Retrofit
import retrofit2.create

class MoviesRemoteDataSource : BaseDataSource() {

    private val api = configureRetrofit()

    suspend fun getTopMovies(page: Int): ReloadableData<MovieResponse> {
        return safeApiCall { api.getTopMovies(TOP_250, page) }
    }

    private fun configureRetrofit(): KinopoiskUnofficialApi {
        val client = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                var request = chain.request()
                val builder = request.newBuilder()
                    .header(X_API_KEY, API_KEY)
                request = builder.build()
                chain.proceed(request)
            })
            .build()

        val json = Json {
            ignoreUnknownKeys = true
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()

        return retrofit.create<KinopoiskUnofficialApi>()
    }

    companion object {
        private const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films/"

        private const val X_API_KEY = "X-API-KEY"
        private const val API_KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"

        const val TOP_POPULAR_MOVIES = "TOP_POPULAR_MOVIES"
        const val TOP_250 = "TOP_250_MOVIES"
    }
}