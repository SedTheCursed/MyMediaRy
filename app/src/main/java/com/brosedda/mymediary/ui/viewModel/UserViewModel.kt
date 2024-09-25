package com.brosedda.mymediary.ui.viewModel

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.brosedda.mymediary.data.DataSource.users
import com.brosedda.mymediary.data.model.User
import com.brosedda.mymediary.ui.state.UserUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(UserUiState(users, User("")))
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    fun setCurrentUser(user: User) {
        _uiState.update { currentState ->
            currentState.copy(
                currentUser = user
            )
        }
    }

    fun setAvatar(@DrawableRes image: Int) {
        _uiState.let {
            val updatedUser = it.value.currentUser.copy(
                avatar = image
            )
            _uiState.update { currentState ->
                currentState.copy(
                    currentUser = updatedUser
                )
            }
        }
    }

    fun addProfile(name: String, password: String?) {
        _uiState.value.let {
            val users = listOf(
                *it.users.toTypedArray(),
                User(
                    name = name,
                    password = password,
                    avatar = it.currentUser.avatar
                )
            )

            _uiState.update { currentState ->
                currentState.copy(
                    users = users
                )
            }
        }
    }
}