package com.brosedda.mymediary.ui.viewModel

import androidx.lifecycle.ViewModel
import com.brosedda.mymediary.data.DataSource.users
import com.brosedda.mymediary.data.model.User
import com.brosedda.mymediary.ui.state.UserUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(UserUiState(users))
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    fun setCurrentUser(user: User) {
        _uiState.update { currentState ->
            currentState.copy(
                currentUser = user
            )
        }
    }
}