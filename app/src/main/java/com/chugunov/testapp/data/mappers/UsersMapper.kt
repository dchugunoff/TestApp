package com.chugunov.testapp.data.mappers

import com.chugunov.testapp.data.network.UserModelDto
import com.chugunov.testapp.data.network.UsersDto
import com.chugunov.testapp.domain.entity.User

class UsersMapper {

    /**
     * Класс маппера, который хранит в себе 2 функции для преобразования сущностей UserDto в User
     */

    fun mapToDomain(usersDto: UsersDto): List<User> {
        return usersDto.users.map { mapToDomain(it) }
    }

    private fun mapToDomain(userDto: UserModelDto): User {
        return User(userDto.name, userDto.age)
    }
}