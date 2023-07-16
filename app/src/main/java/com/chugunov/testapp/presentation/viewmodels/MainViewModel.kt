package com.chugunov.testapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chugunov.testapp.data.RepositoryImpl
import com.chugunov.testapp.data.mappers.UsersMapper
import com.chugunov.testapp.data.network.ApiService
import com.chugunov.testapp.domain.GetUsersUseCase
import com.chugunov.testapp.domain.entity.User
import com.chugunov.testapp.presentation.utils.LoadingState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = RepositoryImpl(
        apiService = ApiService(),
        usersMapper = UsersMapper()
    )

    //Переменная, которая создает экземпляр GetUserUseCase
    private val getUsersUseCase = GetUsersUseCase(repository)


    //Лайвдата которая хранит в себе статус загрузки данных
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> = _loadingState

    //Лайвдата с сохранением суммы двух чисел с первого фрагмента
    private val _sum = MutableLiveData<Int>()
    val sum: LiveData<Int> = _sum

    //Лайвдата, в которой хранится список юзеров
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users


    //Функция, которая вызывает метод getUsers у UseCase и помещает юзеров в лайвдату
    private suspend fun getUsers() {
        _users.postValue(getUsersUseCase.getUsers())
    }

    //Передача значения состояния фрагмента в лайвдату _currentFragmentState

    //Метод расчета суммы двух чисел
    fun calculateSum(firstNumber: Int, secondNumber: Int) {
        _sum.value = firstNumber.plus(secondNumber)
    }

    //Функция, которая вызывается в SecondFragment для имитации загрузки данных
    fun loadData() {
        viewModelScope.launch {
            if (_loadingState.value != LoadingState.Loading) {
                _loadingState.value = LoadingState.Loading
                getUsers()
                delay(2000)
                _loadingState.value = LoadingState.Loaded
            }
        }
    }

}

