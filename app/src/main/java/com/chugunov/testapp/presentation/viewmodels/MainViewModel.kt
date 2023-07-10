package com.chugunov.testapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _currentFragmentState = MutableLiveData<FragmentState>()
    val currentFragmentState: LiveData<FragmentState>
        get() = _currentFragmentState

    fun setCurrentFragmentState(fragmentState: FragmentState) {
        _currentFragmentState.value = fragmentState
    }

}

sealed class FragmentState {
    object MainFragmentState: FragmentState()
    object SecondFragmentState: FragmentState()
}