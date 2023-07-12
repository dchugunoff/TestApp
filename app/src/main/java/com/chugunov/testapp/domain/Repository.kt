package com.chugunov.testapp.domain

import com.chugunov.testapp.domain.entity.User

/**
 * Интерфейс Repository с функцией getUserList
 */
interface Repository {

    suspend fun getUsersList(): List<User>
}