package com.brosedda.mymediary.ui.state

import com.brosedda.mymediary.data.model.User

sealed interface UsersList {
    data class Success(val users: List<User>): UsersList
    data object Loading: UsersList
    data object Error: UsersList
}

data class UserUiState(
    val usersList: UsersList,
    val currentUser: User
)
