package com.brosedda.mymediary.tutorials.cupcake

import android.icu.util.Calendar
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.brosedda.mymediary.R
import com.brosedda.mymediary.assertCurrentRouteName
import com.brosedda.mymediary.onNodeWithStringId
import com.brosedda.mymediary.tutorials.cupcake.ui.CupcakeApp
import com.brosedda.mymediary.tutorials.cupcake.ui.CupcakeScreen
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Locale

class CupcakeScreenNavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            CupcakeApp(navController = navController)
        }
    }

    @Ignore("Test Example")
    @Test
    fun cupcakeNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Ignore("Test Example")
    @Test
    fun cupcakeNavHost_verifyBackNavigationNotShownOnOrderScreen() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Ignore("Test Example")
    @Test
    fun cupcakeNavHost_clickOneCupcake_navigatesToSelectFlavorScreen() {
        composeTestRule.onNodeWithStringId(R.string.one_cupcake)
            .performClick()
        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    }

    @Ignore("Test Example")
    @Test
    fun cupcakeNavHost_clickOnBackOnFlavorScreen_navigatesToStartOrderScreen() {
        navigateToFlavorScreen()
        performNavigateUp()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Ignore("Test Example")
    @Test
    fun cupcakeNavHost_clickNextOnFlavorScreen_navigatesToPickupScreen() {
        navigateToFlavorScreen()
        composeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
        navController.assertCurrentRouteName(CupcakeScreen.Pickup.name)
    }

    @Ignore("Test Example")
    @Test
    fun cupcakeNavHost_clickOnCancelOnFlavorScreen_navigatesToStartScreen() {
        navigateToFlavorScreen()
        composeTestRule.onNodeWithStringId(R.string.cancel)
            .performClick()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Ignore("Test Example")
    @Test
    fun cupcakeNavHost_clickOnBackOnPickupScreen_navigatesToFlavorScreen() {
        navigateToPickupScreen()
        performNavigateUp()
        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    }

    @Ignore("Test Example")
    @Test
    fun cupcakeNavHost_clickNextOnPickupScreen_navigatesToSummaryScreen() {
        navigateToPickupScreen()
        composeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
        navController.assertCurrentRouteName(CupcakeScreen.Summary.name)
    }

    @Ignore("Test Example")
    @Test
    fun cupcakeNavHost_clickOnCancelOnPickupScreen_navigatesToStartScreen() {
        navigateToPickupScreen()
        composeTestRule.onNodeWithStringId(R.string.cancel)
            .performClick()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Ignore("Test Example")
    @Test
    fun cupcakeNavHost_clickOnBackOnSummaryScreen_navigatesToPickupScreen() {
        navigateToSummaryScreen()
        performNavigateUp()
        navController.assertCurrentRouteName(CupcakeScreen.Pickup.name)
    }

    @Ignore("Test Example")
    @Test
    fun cupcakeNavHost_clickOnCancelOnSummaryScreen_navigatesToStartScreen() {
        navigateToSummaryScreen()
        composeTestRule.onNodeWithStringId(R.string.cancel)
            .performClick()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    private fun navigateToFlavorScreen() {
        composeTestRule.onNodeWithStringId(R.string.one_cupcake)
            .performClick()
        composeTestRule.onNodeWithStringId(R.string.chocolate)
            .performClick()
    }

    private fun navigateToPickupScreen() {
        navigateToFlavorScreen()
        composeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
        composeTestRule.onNodeWithText(getFormattedDate())
            .performClick()
    }

    private fun navigateToSummaryScreen() {
        navigateToPickupScreen()
        composeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
    }

    private fun performNavigateUp() {
        composeTestRule.let {
            it.onNodeWithContentDescription(
                it.activity.getString(R.string.back_button)
            ).performClick()
        }
    }

    private fun getFormattedDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(java.util.Calendar.DATE, 1)
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        return formatter.format(calendar.time)
    }
}