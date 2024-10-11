package com.brosedda.mymediary

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule

private typealias Rule = AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>

fun hasStringId(
    @StringRes id: Int,
    rule: Rule,
    substring: Boolean = false,
    ignoreCase: Boolean = false
): SemanticsMatcher = hasText(
    rule.activity.getString(id),
    substring,
    ignoreCase
)