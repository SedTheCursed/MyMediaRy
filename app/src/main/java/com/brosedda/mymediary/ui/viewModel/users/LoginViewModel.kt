package com.brosedda.mymediary.ui.viewModel.users

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    var input by mutableStateOf("")
        private set
    var isWrong by mutableStateOf(false)
        private set

    var isVisible by mutableStateOf(false)
        private set

    fun updateInput(newInput: String) {
        input = newInput
    }

    fun toggleVisibility() {
        isVisible = !isVisible
    }

    fun checkPassword(password: String) {
        isWrong = password != input
    }
}
