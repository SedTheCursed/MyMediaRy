package com.brosedda.mymediary.data.repository

import com.brosedda.mymediary.data.fake.FakeDataSource
import com.brosedda.mymediary.data.model.User
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserRepositoryTest {
    private lateinit var repository: LocalUserRepository

    @Before
    fun setRepository() {
        repository = LocalUserRepository(FakeDataSource.users)
    }

    @Test
    fun userRepository_getUsers_verifyUserList() = runTest {
        assertEquals(FakeDataSource.users, repository.getUsers())
    }

    @Test
    fun userRepository_addUsers_listSizeIncreasedAndLastItemIsUsedAdded() = runTest {
        val user = User("New")

        repository.adduser(user)

        val users = repository.getUsers()

        assertEquals(FakeDataSource.users.size + 1, users.size)
        assertEquals(user, users.last())
    }
}