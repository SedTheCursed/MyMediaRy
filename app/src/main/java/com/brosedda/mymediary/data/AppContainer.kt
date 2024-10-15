package com.brosedda.mymediary.data

import com.brosedda.mymediary.data.DataSource.users
import com.brosedda.mymediary.data.repository.LocalUserRepository
import com.brosedda.mymediary.data.repository.UserRepository

interface AppContainer {
    val userRepository: UserRepository
}

class DefaultAppContainer: AppContainer {
    override val userRepository: UserRepository by lazy {
        LocalUserRepository(users)
    }
}