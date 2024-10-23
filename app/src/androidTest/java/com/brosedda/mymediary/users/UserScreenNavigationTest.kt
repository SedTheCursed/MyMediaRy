package com.brosedda.mymediary.users

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.brosedda.mymediary.R
import com.brosedda.mymediary.assertCurrentRouteName
import com.brosedda.mymediary.onNodeWithStringId
import com.brosedda.mymediary.onNodeWithStringIdDescription
import com.brosedda.mymediary.ui.UserApp
import com.brosedda.mymediary.ui.UsersRoute
import com.brosedda.mymediary.ui.utils.ScreenType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserScreenNavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupUserNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            UserApp(
                navController = navController,
                screenType = ScreenType.COMPACT_PORTRAIT
            )
        }
    }

    @Test
    fun userNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(UsersRoute.Start.name)
    }

    @Test
    fun userNavHost_verifyAppBarNotShownOnProfilesScreen() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun userNavHost_clickOnUserWithPassword_navigateToLoginScreen() {
        composeTestRule.onNodeWithText("UserWithPassword")
            .performClick()
        navController.assertCurrentRouteName(UsersRoute.Login.name)
    }

    @Test
    fun userNavHost_clickOnUserWithoutPassword_navigateToMainActivity() {
        composeTestRule.onNodeWithText("UserWithoutPassword")
            .performClick()
        composeTestRule.onNodeWithText("Hello Android!").assertExists()
    }

    @Test
    fun userNavHost_clickOnCreateProfile_navigateToCreateScreen() {
        composeTestRule.onNodeWithStringId(R.string.add_profile)
            .performClick()
        navController.assertCurrentRouteName(UsersRoute.Create.name)
    }

    @Test
    fun userNavHost_clickOnBackOnLoginScreen_navigateBackToProfilesScreen() {
        composeTestRule.onNodeWithText("UserWithPassword")
            .performClick()
        performNavigateUp()
        navController.assertCurrentRouteName(UsersRoute.Start.name)
    }

    @Test
    fun userNavHost_clickOnLoginButtonWithRightPassword_navigateToMainActivity() {
        composeTestRule.onNodeWithText("UserWithPassword")
            .performClick()
        composeTestRule.onNodeWithStringId(R.string.password)
            .performTextInput("password")
        composeTestRule.onNodeWithStringId(R.string.login)
            .performClick()
        composeTestRule.onNodeWithText("Hello Android!").assertExists()
    }

    @Test
    fun userNavHost_clickOnBackOnCreateScreen_navigateBackToProfilesScreen() {
        composeTestRule.onNodeWithStringId(R.string.add_profile)
            .performClick()
        performNavigateUp()
        navController.assertCurrentRouteName(UsersRoute.Start.name)
    }

    @Test
    fun userNavHost_clickSelectAvatar_navigateToAvatarSelectionScreen() {
        navigateToAvatar()
        navController.assertCurrentRouteName(UsersRoute.Avatar.name)
    }

    @Test
    fun userNavHost_clickOnBackOnAvatarScreen_navigateBackToCreateScreen() {
        navigateToAvatar()
        performNavigateUp()
        navController.assertCurrentRouteName(UsersRoute.Create.name)
    }

    @Test
    fun userNavHost_clickOnChooseAvatar_returnToCreateScreen() {
        navigateToAvatar()
        composeTestRule.onNodeWithStringId(R.string.choose_the_avatar)
            .performClick()

        navController.assertCurrentRouteName(UsersRoute.Create.name)
    }

    private fun performNavigateUp() {
        composeTestRule.onNodeWithStringIdDescription(R.string.back_button)
            .performClick()
    }

    private fun navigateToAvatar() {
        composeTestRule.onNodeWithStringId(R.string.add_profile)
            .performClick()
        composeTestRule.onNodeWithStringId(R.string.choose_avatar)
            .performClick()
    }
}