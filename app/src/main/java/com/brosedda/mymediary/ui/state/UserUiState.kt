package com.brosedda.mymediary.ui.state

import com.brosedda.mymediary.data.model.User

data class UserUiState(
    val users: List<User>,
    val currentUser: User
)
