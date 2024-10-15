package com.brosedda.mymediary.tutorials.reply

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.performClick
import com.brosedda.mymediary.R
import com.brosedda.mymediary.TestCompactWidth
import com.brosedda.mymediary.TestExpandedWidth
import com.brosedda.mymediary.hasStringId
import com.brosedda.mymediary.onNodeWithStringId
import com.brosedda.mymediary.onNodeWithStringIdDescription
import com.brosedda.mymediary.onNodeWithStringIdTag
import com.brosedda.mymediary.tutorials.reply.data.Email
import com.brosedda.mymediary.tutorials.reply.data.local.LocalEmailsDataProvider
import com.brosedda.mymediary.tutorials.reply.ui.ReplyApp
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

class ReplyAppStateRestoration {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Ignore("Test Example")
    @Test
    @TestCompactWidth
    fun compactDevice_configChange_selectedEmailRetained() {
        // Setup compact window
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent {
            ReplyApp(WindowWidthSizeClass.Compact)
        }

        val email = LocalEmailsDataProvider.allEmails[2]

        // Given third email is displayed
        composeTestRule.onNodeWithStringId(email.body)
            .assertIsDisplayed()

        // Open detailed page
        composeTestRule.onNodeWithStringId(email.subject)
            .performClick()

        // Verify that is shows the detail screen for the correct email
        assertShowsDetailedScreenForEmail(email)

        // Simulate a config change
        stateRestorationTester.emulateSavedInstanceStateRestore()

        //Verify that is still shows the detailed screen for the same email
       assertShowsDetailedScreenForEmail(email)
    }

    @Ignore("Test Example")
    @Test
    @TestExpandedWidth
    fun expandedDevice_configChange_selectedEmailRetained() {
        // Setup expanded window
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent {
            ReplyApp(WindowWidthSizeClass.Expanded)
        }

        val email = LocalEmailsDataProvider.allEmails[2]

        // Given third email is displayed
        composeTestRule.onNodeWithStringId(email.body)
            .assertIsDisplayed()

        // Select third email
        composeTestRule.onNodeWithStringId(email.subject)
            .performClick()

        // Verify that third email is displayed on the details screen
        composeTestRule.onNodeWithStringIdTag(R.string.details_screen).onChildren()
            .assertAny(hasAnyDescendant(hasStringId(email.body, composeTestRule)))

        // Simulate a config change
        stateRestorationTester.emulateSavedInstanceStateRestore()

        // Verify that third email is still displayed on the details screen
        composeTestRule.onNodeWithStringIdTag(R.string.details_screen).onChildren()
            .assertAny(hasAnyDescendant(hasStringId(email.body, composeTestRule)))
    }

    private fun assertShowsDetailedScreenForEmail(email: Email) {
        composeTestRule.onNodeWithStringIdDescription(R.string.navigation_back)
            .assertExists()
        composeTestRule.onNodeWithStringId(email.body)
            .assertExists()
    }
}