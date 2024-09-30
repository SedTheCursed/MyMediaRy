package com.brosedda.mymediary.tutorials.cupcake

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.brosedda.mymediary.tutorials.cupcake.ui.SelectOptionScreen
import com.brosedda.mymediary.R
import com.brosedda.mymediary.onNodeWithStringId
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CupCakeOrderScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    // Given list of options
    private val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
    // and subtotal
    private val subtotal = "$100"

    @Before
    fun setupScreen() {
        composeTestRule.setContent {
            SelectOptionScreen(subtotal, flavors)
        }
    }

    @Test
    fun selectOptionScreen_verifyContent() {
        //All options are displayed on the screen
        flavors.forEach { flavor ->
            composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }

        // And the subtotal is displayed correctly
        composeTestRule.let {
            it.onNodeWithText(
                it.activity.getString(R.string.subtotal_price, subtotal)
            ).assertIsDisplayed()
        }

        //And the next button is disabled
        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

    @Test
    fun selectOptionsScreen_chooseAnOption_enableTheNextButton() {
        composeTestRule.let {
            it.onNodeWithText("Vanilla").performClick()
            it.onNodeWithStringId(R.string.next).assertIsEnabled()
        }
    }
}