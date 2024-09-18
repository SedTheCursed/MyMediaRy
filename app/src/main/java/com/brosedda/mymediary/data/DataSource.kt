package com.brosedda.mymediary.data

import com.brosedda.mymediary.R
import com.brosedda.mymediary.data.model.User

object DataSource {
    val users: List<User> = listOf(
        User(
            name = "Sed",
            password = "Dev@6",
            avatar = R.drawable.ic_woof_logo
        ),
        User(
            name = "Anne Onyme"
        )
    )
}