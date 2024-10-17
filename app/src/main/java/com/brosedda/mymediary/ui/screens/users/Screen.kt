package com.brosedda.mymediary.ui.screens.users

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.brosedda.mymediary.ui.utils.ScreenType

@Composable
fun Screen(
    screenType: ScreenType,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    Row(modifier = modifier) {
        if (screenType != ScreenType.COMPACT_PORTRAIT) {
            Spacer(modifier = Modifier.weight(1f))
        }

        Box(Modifier.weight(when (screenType) {
            ScreenType.COMPACT_LANDSCAPE,
             ScreenType.MEDIUM_LANDSCAPE
                 -> 3f
            ScreenType.EXPANDED_LANDSCAPE -> 2f
            else -> 4f
        })) {
            content()
        }
         if (screenType != ScreenType.COMPACT_PORTRAIT) {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}