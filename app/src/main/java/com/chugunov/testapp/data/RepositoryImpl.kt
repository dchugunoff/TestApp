package com.chugunov.testapp.data

import com.chugunov.testapp.data.mappers.UsersMapper
import com.chugunov.testapp.data.network.ApiService
import com.chugunov.testapp.domain.Repository
import com.chugunov.testapp.domain.entity.User

class RepositoryImpl(
    private val apiService: ApiService,
    private val usersMapper: UsersMapper
) : Repository {


    override suspend fun getUsersList(): List<User> {
        val usersDto = apiService.getUsersListDto()
        return usersMapper.mapToDomain(usersDto)
    }
}