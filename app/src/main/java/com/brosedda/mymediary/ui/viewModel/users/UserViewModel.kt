package com.brosedda.mymediary.ui.viewModel.users

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.brosedda.mymediary.MyMediaRyApplication
import com.brosedda.mymediary.data.encrypt
import com.brosedda.mymediary.data.model.User
import com.brosedda.mymediary.data.repository.UserRepository
import com.brosedda.mymediary.ui.state.UserUiState
import com.brosedda.mymediary.ui.state.UsersList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository): ViewModel() {
    private var usersList: UsersList = UsersList.Loading
    private val _uiState = MutableStateFlow(UserUiState(usersList, User("")))
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            usersList = try {
                UsersList.Success(repository.getUsers())
            } catch (e: Exception) {
                UsersList.Error
            }

            _uiState.update { currentState ->
                currentState.copy(
                    usersList = usersList
                )
            }
        }
    }

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
        viewModelScope.launch {
            repository.adduser(
                User(
                    name = name,
                    password = password?.encrypt(),
                    avatar = _uiState.value.currentUser.avatar
                )
            )
        }

        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    usersList = UsersList.Success(repository.getUsers())
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MyMediaRyApplication
                val userRepository = application.container.userRepository
                UserViewModel(repository = userRepository)
            }
        }
    }
}