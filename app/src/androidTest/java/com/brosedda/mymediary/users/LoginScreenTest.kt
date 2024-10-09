package com.brosedda.mymediary.users

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.brosedda.mymediary.R
import com.brosedda.mymediary.data.model.User
import com.brosedda.mymediary.onNodeWithStringId
import com.brosedda.mymediary.onNodeWithStringIdDescription
import com.brosedda.mymediary.ui.screens.users.LoginScreen
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val user = User("User", "password")
    private var isConnected = false

    @Before
    fun setupScreen() {
        composeTestRule.setContent {
            LoginScreen(
                user,
                connect = { isConnected = true }
            )
        }
    }

    @Test
    fun loginScreen_clickOnTheEyeButton_togglePasswordVisibility() {
        composeTestRule.onNodeWithStringId(R.string.password)
            .performTextInput("p")
        composeTestRule.onNodeWithStringIdDescription(R.string.show_password)
            .performClick()
        composeTestRule.onNodeWithText("p")
            .assertExists()
        composeTestRule.onNodeWithStringIdDescription(R.string.hide_password)
            .performClick()
        composeTestRule.onNodeWithText('\u2022'.toString())
            .assertExists()
    }

    @Test
    fun loginScreen_clickOnLoginButtonWithRightPassword_connectCalled() {
        composeTestRule.onNodeWithStringId(R.string.password)
            .performTextInput("password")
        composeTestRule.onNodeWithStringId(R.string.login)
            .performClick()

        assertTrue(isConnected)
    }

    @Test
    fun loginScreen_clickOnLoginButtonWithWrongPassword_textFieldShowError() {
        composeTestRule.onNodeWithStringId(R.string.password)
            .performTextInput("p")
        composeTestRule.onNodeWithStringId(R.string.login)
            .performClick()

        assertFalse(isConnected)
        composeTestRule.onNodeWithStringId(R.string.incorrect_password)
            .assertExists()
    }
}