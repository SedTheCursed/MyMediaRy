package com.brosedda.mymediary.users

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.brosedda.mymediary.R
import com.brosedda.mymediary.data.avatars
import com.brosedda.mymediary.onNodeWithStringId
import com.brosedda.mymediary.onNodeWithStringIdDescription
import com.brosedda.mymediary.ui.screens.users.AvatarSelectionScreen
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AvatarSelectionScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private var selected = R.drawable.avatar_primary

    @Before
    fun setupScreen() {
        composeTestRule.setContent {
            AvatarSelectionScreen(
                chooseAvatar = { selected = it }
            )
        }
    }


    @Test
    fun avatarSelectionScreen_verifyContent() {
        //All avatars are displayed
        avatars.forEach { (_ , description) ->
            composeTestRule.onNodeWithStringIdDescription(description)
                .assertIsDisplayed()
        }

        //First image is highlighted
        composeTestRule.assertImageIsSelected(avatars[0])
    }

    @Test
    fun avatarSelectionScreen_clickOnImage_highlightTransferredToImage() {
        val image = avatars[1]

        composeTestRule.onNodeWithStringIdDescription(image.second)
            .performClick()

        composeTestRule.assertImageIsSelected(image)
        composeTestRule.onAllNodesWithTag("selected", useUnmergedTree = true)
            .assertCountEquals(1)
    }

    @Test
    fun avatarSelectionScreen_clickOnButton_chooseAvatarCalled() {
        val (image, description) = avatars[1]

        composeTestRule.onNodeWithStringIdDescription(description)
            .performClick()
        composeTestRule.onNodeWithStringId(R.string.choose_the_avatar)
            .performClick()

        assertEquals(image, selected)
    }

    private fun <A: ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.assertImageIsSelected(
        image: Pair<Int, Int>
    ) {
        onNodeWithTag("selected", useUnmergedTree = true)
            .assertContentDescriptionEquals(
                composeTestRule.activity.getString(image.second)
            )
    }
}