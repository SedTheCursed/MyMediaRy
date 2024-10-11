package com.brosedda.mymediary

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.SemanticsNodeInteractionCollection
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule

fun <A: ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithStringId(
    @StringRes id: Int
): SemanticsNodeInteraction = onNodeWithText(activity.getString(id))

fun <A: ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onAllNodesWithStringId(
    @StringRes id: Int
): SemanticsNodeInteractionCollection = onAllNodesWithText(activity.getString(id))

fun <A: ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithStringIdDescription(
    @StringRes id: Int
): SemanticsNodeInteraction = onNodeWithContentDescription(activity.getString(id))

fun <A: ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onAllNodesWithStringIdDescription(
    @StringRes id: Int
): SemanticsNodeInteractionCollection = onAllNodesWithContentDescription(activity.getString(id))

fun <A: ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithStringIdTag(
    @StringRes id: Int
): SemanticsNodeInteraction = onNodeWithTag(activity.getString(id))

fun <A: ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onAllNodesWithStringIdTag(
    @StringRes id: Int
): SemanticsNodeInteractionCollection = onAllNodesWithTag(activity.getString(id))
