package com.brosedda.mymediary.ui.test

import com.brosedda.mymediary.ui.viewModel.users.LoginViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LoginViewModelTest {
    private val viewModel = LoginViewModel()
    private val password = "password"

    @Test
    fun loginViewModel_ChangeVisibility_InverseIsVisibleValue() {
        viewModel.let {
            val initialVisibility = it.isVisible
            it.toggleVisibility()

            assertNotEquals(initialVisibility, it.isVisible)
        }
    }

    @Test
    fun loginViewModel_UserTypesSomething_TextFieldUpdatedWithTheInput() {
        val input = "Word"
        viewModel.updateInput(input)
        assertEquals(input, viewModel.input)
    }

    @Test
    fun loginViewModel_UserTypeTheRightPassword_IsWrongIsSetToFalse() {
        viewModel.updateInput("password")
        viewModel.checkPassword(password)

        assertFalse(viewModel.isWrong)
    }

    @Test
    fun loginViewModel_UserTypeTheWrongPassword_IsWrongIsSetToTrue() {
        viewModel.updateInput("PASSword")
        viewModel.checkPassword(password)

        assertTrue(viewModel.isWrong)
    }
}