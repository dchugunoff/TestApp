package com.chugunov.testapp.domain

import com.chugunov.testapp.domain.entity.User

/**
 * GetUsersUseCase, который явялется интерактором, ответсвенный за выполнение операции получения
 * списка пользователей
 */
class GetUsersUseCase(private val repository: Repository) {

    suspend fun getUsers(): List<User> {
        return repository.getUsersList()
    }
}