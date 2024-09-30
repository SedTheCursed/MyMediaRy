package com.brosedda.mymediary.ui.test

import com.brosedda.mymediary.data.DataSource
import com.brosedda.mymediary.data.model.User
import com.brosedda.mymediary.ui.viewModel.users.CreateProfileViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CreateProfileViewModelTest {
    private val viewModel = CreateProfileViewModel()
    private val users = DataSource.users.map { it.name }
    private lateinit var user: User

    private val createUser = { name: String, password: String? ->
        user = User(name, password)
    }

    @Test
    fun createProfileViewModel_ValueOfNameFieldChanged_NameTakesNewValue() {
        val input = "user"
        viewModel.updateName(input, users)

        assertEquals(input, viewModel.name)
    }

    @Test
    fun createProfileViewModel_ValueOfNameFieldChangedToAlreadyExistingAccount_FlagNameHasUsed() {
        val input = "User"
        viewModel.updateName(input, users)

        assertTrue(viewModel.isNameUsed)
    }

    @Test
    fun createProfileViewModel_ValueOfNameFieldChangedFromAlreadyExistingAccount_FlagNameHasUnused() {
        viewModel.updateName("Users", users)
        viewModel.updateName("Users1", users)

        assertFalse(viewModel.isNameUsed)
    }

    @Test
    fun createProfileViewModel_NameTextFieldIsEmpty_FlagNameHasEmpty() {
        val input = ""
        viewModel.updateName(input, users)

        assertTrue(viewModel.isNameEmpty)
    }

    @Test
    fun createProfileViewModel_NameTextFieldContainsOnlyWhitespaces_FlagNameHasEmpty() {
        val input = "     "
        viewModel.updateName(input, users)

        assertTrue(viewModel.isNameEmpty)
    }

    @Test
    fun createProfileViewModel_ValueOfNameFieldChangedFromEmpty_FlagNameHasNotEmpty() {
        viewModel.updateName("    ", users)
        viewModel.updateName("  S  ", users)

        assertFalse(viewModel.isNameEmpty)
    }

    @Test
    fun createProfileViewModel_ValueOfPasswordFieldChanged_PasswordTakesNewValue() {
        val input = "password"
        viewModel.updatePassword(input)

        assertEquals(input, viewModel.password)
    }

    @Test
    fun createProfileViewModel_ModifyPasswordWhenCompareIsNotActive_DoNotCompare() {
        viewModel.updatePassword("password")

        assertNull(viewModel.doesMatch)
    }

    @Test
    fun createProfileViewModel_ModifyPasswordWhenCompareIsActive_CheckIfPasswordAndConfirmationAreIdentical() {
        viewModel.updateConfirmation("password")
        viewModel.updatePassword("passwor")
        viewModel.checkPassword()
        val previousCheck = viewModel.doesMatch

        viewModel.updatePassword("password")

        assertNotEquals(previousCheck, viewModel.doesMatch)
    }

    @Test
    fun createProfileViewModel_ChangeVisibility_InverseIsVisibleValue() {
        viewModel.let {
            val initialVisibility = it.isPasswordVisible
            it.toggleVisibility()

            assertNotEquals(initialVisibility, it.isPasswordVisible)
        }
    }

    @Test
    fun createProfileViewModel_ValueOfConfirmationFieldChanged_ConfirmationTakesNewValue() {
        val input = "password"
        viewModel.updateConfirmation(input)

        assertEquals(input, viewModel.confirmation)
    }

    @Test
    fun createProfileViewModel_ModifyConfirmationWhenCompareIsNotActive_DoNotCompare() {
        viewModel.updateConfirmation("password")

        assertNull(viewModel.doesMatch)
    }

    @Test
    fun createProfileViewModel_ModifyConfirmationWhenCompareIsActive_CheckIfPasswordAndConfirmationAreIdentical() {
        viewModel.updatePassword("password")
        viewModel.updateConfirmation("passwor")
        viewModel.checkPassword()
        val previousCheck = viewModel.doesMatch

        viewModel.updateConfirmation("password")

        assertNotEquals(previousCheck, viewModel.doesMatch)
    }

    @Test
    fun createProfileViewModel_ChangePasswordUsage_PasswordReinitialisedAndInverseWithPasswordValue() {
        viewModel.let {
            it.togglePassword()

            assertFalse(it.withPassword)
            assertEquals("", it.password)
            assertEquals("", it.confirmation)
            assertNull(it.doesMatch)
            assertFalse(it.isPasswordVisible)
            assertFalse(it.isConfirmationFocused)
        }
    }

    @Test
    fun createProfileViewModel_ChangingConfirmationFocus_SetIsConfirmationFocusedAccordingly() {
        viewModel.setConfirmationFocus(true)
        assertTrue(viewModel.isConfirmationFocused)
        viewModel.setConfirmationFocus(false)
        assertFalse(viewModel.isConfirmationFocused)
    }

    @Test
    fun createProfileViewModel_PasswordAndConfirmationAreIdentical_DoesMatchSetTrue() {
        viewModel.checkPassword("password", "password")

        viewModel.doesMatch?.let { assertTrue(it) }
    }

    @Test
    fun createProfileViewModel_PasswordAndConfirmationAreNotIdentical_DoesMatchSetFalse() {
        viewModel.checkPassword("password", "passwor")

        viewModel.doesMatch?.let { assertFalse(it) }
    }

    @Test
    fun createProfileViewModel_NameIsValidAndPasswordMatch_FormSetValid() {
        viewModel.updateName("New User", users)
        viewModel.checkPassword("password", "password")

        assertTrue(viewModel.isValid)
    }

    @Test
    fun createProfileViewModel_NameIsValidAndNoPassword_FormSetValid() {
        viewModel.updateName("New User", users)
        viewModel.togglePassword()

        assertTrue(viewModel.isValid)
    }

    @Test
    fun createProfileViewModel_NameIsUsedAndPasswordMatch_FormSetNotValid() {
        viewModel.updateName("User", users)
        viewModel.checkPassword("password", "password")

        assertFalse(viewModel.isValid)
    }

    @Test
    fun createProfileViewModel_NameIsEmptyAndPasswordMatch_FormSetNotValid() {
        viewModel.updateName("", users)
        viewModel.checkPassword("password", "password")

        assertFalse(viewModel.isValid)
    }

    @Test
    fun createProfileViewModel_NameNeverFocusedAndPasswordMatch_FormSetNotValid() {
        viewModel.checkPassword("password", "password")

        assertFalse(viewModel.isValid)
    }

    @Test
    fun createProfileViewModel_NameIsValidAndPasswordHasNotBeenChecked_FormSetNotValid() {
        viewModel.updateName("New User", users)

        assertFalse(viewModel.isValid)
    }

    @Test
    fun createProfileViewModel_NameIsValidAndPasswordNotMatching_FormSetNotValid() {
        viewModel.updateName("New User", users)
        viewModel.checkPassword("pass", "word")

        assertFalse(viewModel.isValid)
    }

    @Test
    fun createProfileViewModel_FormSentWithPassword_ActionIsCalledWithPassword() {
        viewModel.updateName("User", users)
        viewModel.updatePassword("password")
        viewModel.validateAndCreateProfile(createUser)

        assertEquals("User", user.name)
        assertEquals("password", user.password)
    }

    @Test
    fun createProfileViewModel_FormSentWithoutPassword_ActionIsCalledWithNull() {
        viewModel.updateName("User", users)
        viewModel.togglePassword()
        viewModel.validateAndCreateProfile(createUser)

        assertEquals("User", user.name)
        assertNull(user.password)
    }

    @Test
    fun createProfileViewModel_FormSentWithEmptyPassword_ActionIsCalledWithNull() {
        viewModel.updateName("User", users)
        viewModel.updatePassword(" ")
        viewModel.validateAndCreateProfile(createUser)

        assertEquals("User", user.name)
        assertNull(user.password)
    }
}