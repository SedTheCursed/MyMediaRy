package com.brosedda.mymediary.ui.viewModel.users

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CreateProfileViewModel: ViewModel() {
    var name by mutableStateOf("")
        private set

    var isNameUsed by mutableStateOf(false)
        private set

    var isNameEmpty by mutableStateOf(false)
        private set

    var withPassword by mutableStateOf(true)
        private set

    var password by mutableStateOf("")
        private set

    var confirmation by mutableStateOf("")
        private set

    var isPasswordVisible by mutableStateOf(false)
        private set

    var doesMatch: Boolean? by mutableStateOf(null)
        private set

    var isValid by mutableStateOf(false)
        private set

    var isConfirmationFocused by mutableStateOf(false)
        private set

    private var nameGotFocus = false

    fun updateName(input: String, users: List<String>) {
        if (!nameGotFocus) nameGotFocus = true
        name = input
        isNameUsed = users.contains(input)
        isNameEmpty = input.trim().isEmpty()
        checkValidity()
    }

    fun updatePassword(input: String) {
        password = input
        if (doesMatch != null) checkPassword(password = input)
    }

    fun updateConfirmation(input: String) {
        confirmation = input
        if (doesMatch != null) checkPassword(confirmation = input)
    }

    fun setConfirmationFocus(b: Boolean) {
        isConfirmationFocused = b
    }

    fun toggleVisibility() {
        isPasswordVisible = !isPasswordVisible
    }

    fun togglePassword() {
        withPassword = !withPassword
        resetPassword()
        checkValidity()
    }

    fun checkPassword(
        password: String = this.password,
        confirmation: String = this.confirmation
    ) {
        doesMatch = password == confirmation
        checkValidity()
    }

    fun validateAndCreateProfile(action: (String, String?) -> Unit) {
        val pwd = if (!withPassword || password.trim().isEmpty()) null else password
        action(name, pwd)
    }

    private fun checkValidity() {
        val nameValidity = !(isNameUsed || isNameEmpty) && nameGotFocus
        val passwordValidity = !withPassword || (doesMatch == true)

        isValid = nameValidity && passwordValidity
    }

    private fun resetPassword() {
        password = ""
        confirmation = ""
        isPasswordVisible = false
        doesMatch = null
        isConfirmationFocused = false
    }
}
