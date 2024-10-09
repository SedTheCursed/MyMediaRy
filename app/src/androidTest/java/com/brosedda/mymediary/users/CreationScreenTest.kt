package com.brosedda.mymediary.users

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.isNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import com.brosedda.mymediary.R
import com.brosedda.mymediary.data.DataSource.users
import com.brosedda.mymediary.data.model.User
import com.brosedda.mymediary.onAllNodesWithStringId
import com.brosedda.mymediary.onAllNodesWithStringIdDescription
import com.brosedda.mymediary.onNodeWithStringId
import com.brosedda.mymediary.ui.screens.users.CreationScreen
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CreationScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var user: User

    @Before
    fun setupScreen() {
        composeTestRule.setContent {
            CreationScreen(
                users = users.map { it.name },
                avatar = R.drawable.add_circle,
                chooseAvatar = {},
                addProfile = { name, password ->
                    user = User(name, password)
                }
            )
        }
    }

    @Test
    fun creationScreen_verifyContent() {
        composeTestRule.onNodeWithTag(R.drawable.add_circle.toString(), useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule.onNodeWithStringId(R.string.create_profile)
            .assertIsNotEnabled()
    }

    @Test
    fun creationScreen_nameAlreadyExisting_textFieldDisplayError() {
        typeExistingAccountName()

        composeTestRule.onNodeWithStringId(R.string.used_name)
            .assertIsDisplayed()
    }

    @Test
    fun creationScreen_emptyName_textFieldDisplayError() {
        composeTestRule.onNodeWithStringId(R.string.profile_name).performTextInput("p")
        composeTestRule.onNodeWithStringId(R.string.profile_name).performTextClearance()

        composeTestRule.onNodeWithStringId(R.string.mandatory_name)
            .assertIsDisplayed()
    }

    @Test
    fun creationScreen_nameChangedAfterUsedNameError_errorDisappear() {
        typeExistingAccountName()
        composeTestRule.onNodeWithStringId(R.string.used_name)
            .performTextInput("UserWithPassword1")

        composeTestRule.onNodeWithStringId(R.string.profile_name)
            .assertIsDisplayed()
    }

    @Test
    fun creationScreen_nameChangedAfterMandatoryNameError_errorDisappear() {
        typeEmptyAccountName()
        composeTestRule.onNodeWithStringId(R.string.mandatory_name)
            .performTextInput(" p")

        composeTestRule.onNodeWithStringId(R.string.profile_name)
            .assertIsDisplayed()
    }

    @Test
    fun creationScreen_clickOnTheEyeButton_togglePasswordVisibility() {
        composeTestRule.onNodeWithStringId(R.string.password)
            .performTextInput("p")
        composeTestRule.onNodeWithStringId(R.string.password_confirmation)
            .performTextInput("p")
        composeTestRule.onAllNodesWithStringIdDescription(R.string.show_password)
            .onFirst()
            .performClick()

        composeTestRule.onAllNodesWithText("p")
            .assertCountEquals(2)

        composeTestRule.onAllNodesWithStringIdDescription(R.string.hide_password)
            .onFirst()
            .performClick()

        composeTestRule.onAllNodesWithText('\u2022'.toString())
            .assertCountEquals(2)
    }

    @Test
    fun creationScreen_confirmationFieldLoseFocusOnce_passwordAndConfirmationAreCompared() {
        activatePasswordCheck(false)

        composeTestRule.onAllNodesWithStringId(R.string.not_matching_password)
            .assertCountEquals(2)
    }

    @Test
    fun creationScreen_passwordOrConfirmationChangedAfterCheckActivation_checkIfPasswordAndConfirmationMatch() {
        activatePasswordCheck()
        composeTestRule.onNodeWithStringId(R.string.password)
            .performTextInput("2")

        composeTestRule.onAllNodesWithStringId(R.string.not_matching_password)
            .assertCountEquals(2)

        composeTestRule.onAllNodesWithStringId(R.string.not_matching_password)
            .onLast()
            .performTextInput("2")

        composeTestRule.onAllNodesWithStringId(R.string.not_matching_password)
            .assertCountEquals(0)
    }

    @Test
    fun creationScreen_checkCheckbox_ToggleDisabilityPasswordAndConfirmationAndResetThem() {
        activatePasswordCheck(false)
        composeTestRule.onNodeWithStringId(R.string.no_password)
            .performClick()

        // Password and Confirmation TextFields are disabled
        // Button is disabled at loading
        composeTestRule.onAllNodes(isNotEnabled())
            .assertCountEquals(3)

        // Fields are empty
        composeTestRule.onNodeWithText("password")
            .assertDoesNotExist()
        composeTestRule.onNodeWithText("pass")
            .assertDoesNotExist()

        // Deactivate password check
        composeTestRule.onAllNodesWithStringId(R.string.not_matching_password)
            .assertCountEquals(0)

        composeTestRule.onNodeWithStringId(R.string.no_password)
            .performClick()

        //Field are enabled again
        composeTestRule.onAllNodes(isNotEnabled())
            .assertCountEquals(1)
    }

    @Test
    fun creationScreen_nameIsValidAndPasswordMatched_buttonIsEnabled() {
        enableButton()

        composeTestRule.onNodeWithStringId(R.string.create_profile)
            .assertIsEnabled()
    }

    @Test
    fun creationScreen_nameIsValidAndNoPasswordIsChecked_buttonIsEnabled() {
        enableButton(false)

        composeTestRule.onNodeWithStringId(R.string.create_profile)
            .assertIsEnabled()
    }

    @Test
    fun creationScreen_usedNameWhenButtonIsEnabled_buttonIsDisabled() {
        enableButton()

        composeTestRule.onNodeWithStringId(R.string.profile_name)
            .performTextInput("WithPassword")

        composeTestRule.onNodeWithStringId(R.string.create_profile)
            .assertIsNotEnabled()
    }

    @Test
    fun creationScreen_emptyNameWhenButtonIsEnabled_buttonIsDisabled() {
        enableButton()

        composeTestRule.onNodeWithStringId(R.string.profile_name)
            .performTextClearance()

        composeTestRule.onNodeWithStringId(R.string.create_profile)
            .assertIsNotEnabled()
    }

    @Test
    fun creationScreen_uncheckNoPasswordWhenButtonIsEnabled_buttonDisabled() {
        enableButton(false)

        composeTestRule.onNodeWithStringId(R.string.no_password)
            .performClick()

        composeTestRule.onNodeWithStringId(R.string.create_profile)
            .assertIsNotEnabled()
    }

    @Test
    fun creationScreen_unmatchedPasswordWhenButtonIsEnabled_buttonDisabled() {
        enableButton()

        composeTestRule.onNodeWithStringId(R.string.password)
            .performTextInput("d")

        composeTestRule.onNodeWithStringId(R.string.create_profile)
            .assertIsNotEnabled()
    }

    @Test
    fun creationScreen_buttonClicked_addProfileCalled() {
        enableButton()

        composeTestRule.onNodeWithStringId(R.string.create_profile)
            .performClick()

        assertEquals("User", user.name)
        assertEquals("password", user.password)
    }

    private fun typeExistingAccountName() {
        composeTestRule.onNodeWithStringId(R.string.profile_name)
            .performTextInput("UserWithPassword")
    }

    private fun typeEmptyAccountName() {
        composeTestRule.onNodeWithStringId(R.string.profile_name)
            .performTextInput(" ")
    }

    private fun activatePasswordCheck(matchingPassword: Boolean = true) {
        val confirmation = if (matchingPassword) "password" else "wrong"

        composeTestRule.onNodeWithStringId(R.string.password)
            .performTextInput("password")
        composeTestRule.onNodeWithStringId(R.string.password_confirmation)
            .performTextInput(confirmation)
        composeTestRule.onNodeWithStringId(R.string.profile_name)
            .performClick()
    }

    private fun enableButton(withPassword: Boolean = true) {
        composeTestRule.onNodeWithStringId(R.string.profile_name)
            .performTextInput("User")

        when (withPassword) {
            true -> activatePasswordCheck()
            false -> composeTestRule.onNodeWithStringId(R.string.no_password)
                .performClick()
        }
    }
}