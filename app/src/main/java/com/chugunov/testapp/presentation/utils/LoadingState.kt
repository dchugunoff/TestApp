package com.chugunov.testapp.presentation.utils

/**
 * Sealed класс для состояния загрузки
 */
sealed class LoadingState {
    object Loading: LoadingState()
    object Loaded: LoadingState()
}