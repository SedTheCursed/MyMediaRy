package com.brosedda.mymediary.ui.viewModel.users

import com.brosedda.mymediary.data.fake.FakeDataSource
import com.brosedda.mymediary.data.model.User
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CreateProfileViewModelTest {
    private val viewModel = CreateProfileViewModel()
    private val users = FakeDataSource.users.map { it.name }
    private lateinit var user: User

    private val createUser = { name: String, password: String? ->
        user = User(name, password)
    }

    @Test
    fun createProfileViewModel_valueOfNameFieldChanged_nameTakesNewValue() {
        val input = "user"
        viewModel.updateName(input, users)

        assertEquals(input, viewModel.name)
    }

    @Test
    fun createProfileViewModel_valueOfNameFieldChangedToAlreadyExistingAccount_flagNameHasUsed() {
        val input = "User"
        viewModel.updateName(input, users)

        assertTrue(viewModel.isNameUsed)
    }

    @Test
    fun createProfileViewModel_valueOfNameFieldChangedFromAlreadyExistingAccount_flagNameHasUnused() {
        viewModel.updateName("Users", users)
        viewModel.updateName("Users1", users)

        assertFalse(viewModel.isNameUsed)
    }

    @Test
    fun createProfileViewModel_nameTextFieldIsEmpty_flagNameHasEmpty() {
        val input = ""
        viewModel.updateName(input, users)

        assertTrue(viewModel.isNameEmpty)
    }

    @Test
    fun createProfileViewModel_nameTextFieldContainsOnlyWhitespaces_flagNameHasEmpty() {
        val input = "     "
        viewModel.updateName(input, users)

        assertTrue(viewModel.isNameEmpty)
    }

    @Test
    fun createProfileViewModel_valueOfNameFieldChangedFromEmpty_flagNameHasNotEmpty() {
        viewModel.updateName("    ", users)
        viewModel.updateName("  S  ", users)

        assertFalse(viewModel.isNameEmpty)
    }

    @Test
    fun createProfileViewModel_valueOfPasswordFieldChanged_passwordTakesNewValue() {
        val input = "password"
        viewModel.updatePassword(input)

        assertEquals(input, viewModel.password)
    }

    @Test
    fun createProfileViewModel_modifyPasswordWhenCompareIsNotActive_doNotCompare() {
        viewModel.updatePassword("password")

        assertNull(viewModel.doesMatch)
    }

    @Test
    fun createProfileViewModel_modifyPasswordWhenCompareIsActive_checkIfPasswordAndConfirmationAreIdentical() {
        viewModel.updateConfirmation("password")
        viewModel.updatePassword("passwor")
        viewModel.checkPassword()
        val previousCheck = viewModel.doesMatch

        viewModel.updatePassword("password")

        assertNotEquals(previousCheck, viewModel.doesMatch)
    }

    @Test
    fun createProfileViewModel_changeVisibility_inverseIsVisibleValue() {
        viewModel.let {
            val initialVisibility = it.isPasswordVisible
            it.toggleVisibility()

            assertNotEquals(initialVisibility, it.isPasswordVisible)
        }
    }

    @Test
    fun createProfileViewModel_valueOfConfirmationFieldChanged_confirmationTakesNewValue() {
        val input = "password"
        viewModel.updateConfirmation(input)

        assertEquals(input, viewModel.confirmation)
    }

    @Test
    fun createProfileViewModel_modifyConfirmationWhenCompareIsNotActive_doNotCompare() {
        viewModel.updateConfirmation("password")

        assertNull(viewModel.doesMatch)
    }

    @Test
    fun createProfileViewModel_modifyConfirmationWhenCompareIsActive_checkIfPasswordAndConfirmationAreIdentical() {
        viewModel.updatePassword("password")
        viewModel.updateConfirmation("passwor")
        viewModel.checkPassword()
        val previousCheck = viewModel.doesMatch

        viewModel.updateConfirmation("password")

        assertNotEquals(previousCheck, viewModel.doesMatch)
    }

    @Test
    fun createProfileViewModel_changePasswordUsage_passwordReinitialisedAndInverseWithPasswordValue() {
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
    fun createProfileViewModel_changingConfirmationFocus_setIsConfirmationFocusedAccordingly() {
        viewModel.setConfirmationFocus(true)
        assertTrue(viewModel.isConfirmationFocused)
        viewModel.setConfirmationFocus(false)
        assertFalse(viewModel.isConfirmationFocused)
    }

    @Test
    fun createProfileViewModel_passwordAndConfirmationAreIdentical_doesMatchSetTrue() {
        viewModel.checkPassword("password", "password")

        viewModel.doesMatch?.let { assertTrue(it) }
    }

    @Test
    fun createProfileViewModel_passwordAndConfirmationAreNotIdentical_doesMatchSetFalse() {
        viewModel.checkPassword("password", "passwor")

        viewModel.doesMatch?.let { assertFalse(it) }
    }

    @Test
    fun createProfileViewModel_nameIsValidAndPasswordMatch_formSetValid() {
        viewModel.updateName("New User", users)
        viewModel.checkPassword("password", "password")

        assertTrue(viewModel.isValid)
    }

    @Test
    fun createProfileViewModel_nameIsValidAndNoPassword_formSetValid() {
        viewModel.updateName("New User", users)
        viewModel.togglePassword()

        assertTrue(viewModel.isValid)
    }

    @Test
    fun createProfileViewModel_nameIsUsedAndPasswordMatch_formSetNotValid() {
        viewModel.updateName("User", users)
        viewModel.checkPassword("password", "password")

        assertFalse(viewModel.isValid)
    }

    @Test
    fun createProfileViewModel_nameIsEmptyAndPasswordMatch_formSetNotValid() {
        viewModel.updateName("", users)
        viewModel.checkPassword("password", "password")

        assertFalse(viewModel.isValid)
    }

    @Test
    fun createProfileViewModel_nameNeverFocusedAndPasswordMatch_formSetNotValid() {
        viewModel.checkPassword("password", "password")

        assertFalse(viewModel.isValid)
    }

    @Test
    fun createProfileViewModel_nameIsValidAndPasswordHasNotBeenChecked_formSetNotValid() {
        viewModel.updateName("New User", users)

        assertFalse(viewModel.isValid)
    }

    @Test
    fun createProfileViewModel_nameIsValidAndPasswordNotMatching_formSetNotValid() {
        viewModel.updateName("New User", users)
        viewModel.checkPassword("pass", "word")

        assertFalse(viewModel.isValid)
    }

    @Test
    fun createProfileViewModel_formSentWithPassword_actionIsCalledWithPassword() {
        viewModel.updateName("User", users)
        viewModel.updatePassword("password")
        viewModel.validateAndCreateProfile(createUser)

        assertEquals("User", user.name)
        assertEquals("password", user.password)
    }

    @Test
    fun createProfileViewModel_formSentWithoutPassword_actionIsCalledWithNull() {
        viewModel.updateName("User", users)
        viewModel.togglePassword()
        viewModel.validateAndCreateProfile(createUser)

        assertEquals("User", user.name)
        assertNull(user.password)
    }

    @Test
    fun createProfileViewModel_formSentWithEmptyPassword_actionIsCalledWithNull() {
        viewModel.updateName("User", users)
        viewModel.updatePassword(" ")
        viewModel.validateAndCreateProfile(createUser)

        assertEquals("User", user.name)
        assertNull(user.password)
    }
}