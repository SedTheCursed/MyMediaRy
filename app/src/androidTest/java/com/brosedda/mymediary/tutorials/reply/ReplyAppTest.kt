package com.brosedda.mymediary.tutorials.reply

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.brosedda.mymediary.R
import com.brosedda.mymediary.TestCompactWidth
import com.brosedda.mymediary.TestExpandedWidth
import com.brosedda.mymediary.TestMediumWidth
import com.brosedda.mymediary.onNodeWithStringIdTag
import com.brosedda.mymediary.tutorials.reply.ui.ReplyApp
import org.junit.Rule
import org.junit.Test

class ReplyAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    @TestCompactWidth
    fun compactDevice_verifyUsingBottomNavigation() {
        //Set up compact window
        composeTestRule.setContent {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Compact
            )
        }

        //Bottom navigation is displayed
        composeTestRule.onNodeWithStringIdTag(R.string.navigation_bottom)
            .assertExists()
    }

    @Test
    @TestMediumWidth
    fun mediumDevice_verifyUsingNavigationRail() {
        //Set up medium window
        composeTestRule.setContent {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Medium
            )
        }

        //Navigation Rail is displayed
        composeTestRule.onNodeWithStringIdTag(R.string.navigation_rail)
            .assertExists()
    }

    @Test
    @TestExpandedWidth
    fun expandedDevice_verifyUsingNavigationDrawer() {
        //Set up expanded window
        composeTestRule.setContent {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Expanded
            )
        }

        //Navigation drawer is displayed
        composeTestRule.onNodeWithStringIdTag(R.string.navigation_drawer)
            .assertExists()
    }
}