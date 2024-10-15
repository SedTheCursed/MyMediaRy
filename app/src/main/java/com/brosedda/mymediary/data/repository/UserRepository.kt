package com.brosedda.mymediary.data.repository

import com.brosedda.mymediary.data.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
    suspend fun adduser(user: User)
}

class LocalUserRepository(fileUsers: List<User>): UserRepository {
    private var _users = mutableListOf(*fileUsers.toTypedArray())
    val users = _users

    override suspend fun getUsers(): List<User> = users

    override suspend fun adduser(user: User) {
        _users.add(user)
    }
}