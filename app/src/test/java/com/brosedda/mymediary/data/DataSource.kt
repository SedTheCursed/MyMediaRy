package com.brosedda.mymediary.data

import com.brosedda.mymediary.R
import com.brosedda.mymediary.data.model.User

object DataSource {
    val users: List<User> = listOf(
        User(
            name = "User",
            password = "password",
            avatar = R.drawable.account_circle
        )
    )
}