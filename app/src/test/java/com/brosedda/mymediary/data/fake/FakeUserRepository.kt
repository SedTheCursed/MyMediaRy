package com.brosedda.mymediary.data.fake

import com.brosedda.mymediary.data.model.User
import com.brosedda.mymediary.data.repository.UserRepository

class FakeUserRepository: UserRepository {
    private val users = mutableListOf(*FakeDataSource.users.toTypedArray())
    override suspend fun getUsers(): List<User> = users
    override suspend fun adduser(user: User) {
        users.add(user)
    }
}