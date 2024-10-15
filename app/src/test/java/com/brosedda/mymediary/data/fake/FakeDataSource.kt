package com.brosedda.mymediary.data.fake

import com.brosedda.mymediary.R
import com.brosedda.mymediary.data.model.User

object FakeDataSource {
    val users: List<User> = listOf(
        User(
            name = "User",
            password = "password",
            avatar = R.drawable.account_circle
        )
    )
}