package org.birdper.movies

data class ReloadableData<T>(val value: T? = null, val status: Status)

sealed class Status
data object Idle: Status()
data object Loading: Status()
data class Error(val err: String): Status()