package com.chugunov.testapp.domain

import com.chugunov.testapp.domain.entity.User

class GetUsersUseCase(private val repository: Repository) {

    suspend fun getUsers(): List<User> {
        return repository.getUsersList()
    }
}