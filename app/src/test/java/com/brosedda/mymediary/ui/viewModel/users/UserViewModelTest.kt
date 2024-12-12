package com.brosedda.mymediary.ui.viewModel.users

import com.brosedda.mymediary.R
import com.brosedda.mymediary.data.fake.FakeUserRepository
import com.brosedda.mymediary.data.model.User
import com.brosedda.mymediary.data.verify
import com.brosedda.mymediary.rules.TestDispatcherRule
import com.brosedda.mymediary.ui.state.UsersList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var viewModel: UserViewModel

    @Before
    fun setViewModel() = runTest {
        viewModel = UserViewModel(repository = FakeUserRepository())
    }

    @Test
    fun userViewModel_userChosen_currentUserIsSet() {
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
    fun userViewModel_profileAvatarChosen_currentUserAvatarIsSet() {
        viewModel.setAvatar(R.drawable.avatar_primary)
        val avatar = viewModel.uiState.value.currentUser.avatar

        assertEquals(R.drawable.avatar_primary, avatar)
    }

    @Test
    fun userViewModel_profileWithPasswordCreated_newProfileAddedToProfileList() {
        val usersList = viewModel.uiState.value.usersList as UsersList.Success
        val numberOfProfile = usersList.users.size
        viewModel.addProfile("user", "password")

        usersList.users.let {
            val user = it.last()

            assertEquals(numberOfProfile + 1, it.size)
            assertEquals("user", user.name)
            assertNotNull(user.password)
            assertTrue(user.password!!.verify("password"))
        }
    }

    @Test
    fun userViewModel_profileWithoutPasswordCreated_newProfileAddedToProfileList() {
        viewModel.addProfile("user", null)

        (viewModel.uiState.value.usersList as UsersList.Success).users.let {
            val user = it.last()

            assertEquals(2, it.size)
            assertEquals("user", user.name)
            assertNull(user.password)
        }
    }
}