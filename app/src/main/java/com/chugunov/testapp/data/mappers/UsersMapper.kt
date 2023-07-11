package com.chugunov.testapp.data.mappers

import com.chugunov.testapp.data.network.UserModelDto
import com.chugunov.testapp.data.network.UsersDto
import com.chugunov.testapp.domain.entity.User

class UsersMapper {

    fun mapToDomain(usersDto: UsersDto): List<User> {
        return usersDto.users.map { mapToDomain(it) }
    }

    private fun mapToDomain(userDto: UserModelDto): User {
        return User(userDto.name, userDto.age)
    }
}