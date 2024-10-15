package com.brosedda.mymediary.ui.viewModel.users

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LoginViewModelTest {
    private val viewModel = LoginViewModel()
    private val password = "password"

    @Test
    fun loginViewModel_changeVisibility_inverseIsVisibleValue() {
        viewModel.let {
            val initialVisibility = it.isVisible
            it.toggleVisibility()

            assertNotEquals(initialVisibility, it.isVisible)
        }
    }

    @Test
    fun loginViewModel_userTypesSomething_textFieldUpdatedWithTheInput() {
        val input = "Word"
        viewModel.updateInput(input)
        assertEquals(input, viewModel.input)
    }

    @Test
    fun loginViewModel_userTypeTheRightPassword_isWrongIsSetToFalse() {
        viewModel.updateInput("password")
        viewModel.checkPassword(password)

        assertFalse(viewModel.isWrong)
    }

    @Test
    fun loginViewModel_userTypeTheWrongPassword_isWrongIsSetToTrue() {
        viewModel.updateInput("PASSword")
        viewModel.checkPassword(password)

        assertTrue(viewModel.isWrong)
    }
}