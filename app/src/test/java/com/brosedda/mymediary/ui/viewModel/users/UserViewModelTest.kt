package com.brosedda.mymediary.ui.test

import com.brosedda.mymediary.ui.viewModel.users.UserViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import com.brosedda.mymediary.R
import com.brosedda.mymediary.data.model.User
import org.junit.Assert.assertNull

class UserViewModelTest {
    private val viewModel = UserViewModel()

    @Test
    fun userViewModel_UserChosen_CurrentUserIsSet() {
        val user = User(
            name = "User",
            password = "password"
        )
        viewModel.setCurrentUser(user)

        viewModel.uiState.value.currentUser.let {
            assertEquals("User", it.name)
            assertEquals("password", it.password)
            assertEquals(R.drawable.account_circle, user.avatar)
        }
    }

    @Test
    fun userViewModel_ProfileAvatarChosen_CurrentUserAvatarIsSet() {
        viewModel.setAvatar(R.drawable.avatar_primary)
        val avatar = viewModel.uiState.value.currentUser.avatar

        assertEquals(R.drawable.avatar_primary, avatar)
    }

    @Test
    fun userViewModel_ProfileWithPasswordCreated_NewProfileAddedToProfileList() {
        val numberOfProfile = viewModel.uiState.value.users.size
        viewModel.addProfile("user", "password")

        viewModel.uiState.value.users.let {
            val user = it.last()

            assertEquals(numberOfProfile + 1, it.size)
            assertEquals("user", user.name)
            assertEquals("password", user.password)
        }
    }

    @Test
    fun userViewModel_ProfileWithoutPasswordCreated_NewProfileAddedToProfileList() {
        viewModel.addProfile("user", null)

        viewModel.uiState.value.users.let {
            val user = it.last()

            assertEquals(2, it.size)
            assertEquals("user", user.name)
            assertNull(user.password)
        }
    }
}