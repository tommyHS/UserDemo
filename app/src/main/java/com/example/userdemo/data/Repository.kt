package com.example.userdemo.data

import com.example.userdemo.network.User
import com.example.userdemo.network.UsersApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultRepository(private val apiService: UsersApiService) : Repository {
    override fun getUsers(since: Int?, perPage: Int?): Flow<List<User>> = flow {
        val result = apiService.getUsers(since, perPage)
        emit(result)
    }
}

interface Repository {
    fun getUsers(since: Int?, perPage: Int?): Flow<List<User>>
}