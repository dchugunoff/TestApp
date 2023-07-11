package com.chugunov.testapp.presentation.utils

sealed class LoadingState {
    object Loading: LoadingState()
    object Loaded: LoadingState()
}