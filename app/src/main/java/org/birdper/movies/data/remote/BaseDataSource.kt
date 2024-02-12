package org.birdper.movies.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.ResponseBody
import org.birdper.movies.Idle
import org.birdper.movies.Error
import org.birdper.movies.ReloadableData
import org.birdper.movies.data.remote.model.ApiError
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseDataSource {

    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): ReloadableData<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCalled()

                if (response.isSuccessful) {
                    ReloadableData(value = response.body(), Idle)
                } else {
                    val errorResponse: ApiError? = convertErrorBody(response.errorBody())

                    ReloadableData(status = Error(errorResponse?.message ?: "Something went wrong"))
                }

            } catch (e: HttpException) {
                ReloadableData(status = Error(e.message ?: "Something went wrong"))
            } catch (e: IOException) {
                ReloadableData(status = Error("Please check your network connection"))
            } catch (e: Exception) {
                ReloadableData(status = Error("Something went wrong"))
            }
        }
    }

    private fun convertErrorBody(errorBody: ResponseBody?): ApiError? {
        return try {
            errorBody?.source()?.let {
                Json.decodeFromString(ApiError.serializer(), errorBody.string())
            }
        } catch (exception: Exception) {
            null
        }
    }
}
