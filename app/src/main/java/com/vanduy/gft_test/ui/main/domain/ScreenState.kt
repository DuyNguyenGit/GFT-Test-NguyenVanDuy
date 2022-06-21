package com.vanduy.gft_test.ui.main.domain

sealed class ScreenState<T> {
    class Success<T>(val data: T) : ScreenState<T>()
    class Error<T>(val error: Throwable) : ScreenState<T>()
    class Loading<T>(val isLoading: Boolean) : ScreenState<T>()

    companion object {

        fun <T> loading(isLoading: Boolean): ScreenState<T> = Loading(isLoading)

        fun <T> error(error: Throwable): ScreenState<T> = Error(error)

        fun <T> success(data: T): ScreenState<T> = Success(data)
    }
}
