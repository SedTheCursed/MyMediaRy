package com.brosedda.mymediary.users

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.brosedda.mymediary.R
import com.brosedda.mymediary.data.DataSource.users
import com.brosedda.mymediary.onNodeWithStringId
import com.brosedda.mymediary.ui.screens.users.ProfilesScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProfilesScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setupScreen() {
        composeTestRule.setContent {
            ProfilesScreen(
                users,
                navigateToLogin = { _ -> },
                navigateToCreation = { }
            )
        }
    }

    @Test
    fun profilesScreen_verifyContent() {
        users.forEach { (name, _, _) ->
            composeTestRule.onNodeWithText(name)
                .assertExists()
        }

        composeTestRule.onNodeWithStringId(R.string.add_profile)
            .assertExists()
    }
}