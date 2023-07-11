package com.chugunov.testapp.domain

import com.chugunov.testapp.domain.entity.User

interface Repository {

    suspend fun getUsersList(): List<User>
}