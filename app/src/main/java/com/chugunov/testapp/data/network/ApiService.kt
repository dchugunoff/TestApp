package com.chugunov.testapp.data.network

import com.google.gson.Gson

class ApiService {

    private val json = """
        {
            "users": [
                {
                    "name": "Misha",
                    "age": "20"
                },
                {
                    "name": "Dmitry",
                    "age": "21"
                },
                {
                    "name": "Elena",
                    "age": "18"
                },
                {
                    "name": "Pavel",
                    "age": "25"
                }
            ]
        }
    """.trimIndent()

    fun getUsersListDto(): UsersDto {
        return Gson().fromJson(json, UsersDto::class.java)
    }
}