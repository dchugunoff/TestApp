package com.chugunov.testapp.presentation.utils

sealed class FragmentState {
    object MainFragmentState : FragmentState()
    object SecondFragmentState : FragmentState()
}