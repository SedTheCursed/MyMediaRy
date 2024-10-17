package com.brosedda.mymediary.ui.utils

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

enum class ScreenSize {
    COMPACT, MEDIUM, EXPANDED
}

enum class Orientation {
    PORTRAIT, LANDSCAPE
}

enum class ScreenType(val type: ScreenSize, val orientation: Orientation) {
    COMPACT_PORTRAIT(ScreenSize.COMPACT, Orientation.PORTRAIT),
    COMPACT_LANDSCAPE(ScreenSize.COMPACT, Orientation.LANDSCAPE),
    MEDIUM_PORTRAIT(ScreenSize.MEDIUM, Orientation.PORTRAIT),
    MEDIUM_LANDSCAPE(ScreenSize.MEDIUM, Orientation.LANDSCAPE),
    EXPANDED_PORTRAIT(ScreenSize.EXPANDED, Orientation.PORTRAIT),
    EXPANDED_LANDSCAPE(ScreenSize.EXPANDED, Orientation.LANDSCAPE)
}

fun getScreenType(windowsSize: WindowSizeClass): ScreenType {
    val width = windowsSize.widthSizeClass
    val height = windowsSize.heightSizeClass

    return when {
        width == WindowWidthSizeClass.Compact
            -> ScreenType.COMPACT_PORTRAIT
        height == WindowHeightSizeClass.Compact
            -> ScreenType.COMPACT_LANDSCAPE
        height == WindowHeightSizeClass.Expanded
            -> ScreenType.EXPANDED_PORTRAIT
        width == WindowWidthSizeClass.Expanded
            -> ScreenType.EXPANDED_LANDSCAPE
        width == WindowWidthSizeClass.Medium
            -> ScreenType.MEDIUM_PORTRAIT
        height == WindowHeightSizeClass.Medium
            -> ScreenType.MEDIUM_LANDSCAPE
        else -> ScreenType.COMPACT_PORTRAIT
    }
}