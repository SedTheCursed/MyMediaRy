package com.brosedda.mymediary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.brosedda.mymediary.ui.UserApp
import com.brosedda.mymediary.ui.theme.MyMediaRyTheme
import com.brosedda.mymediary.ui.utils.getScreenType

class UserActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyMediaRyTheme(darkTheme = false) {
                val windowSize = calculateWindowSizeClass(this)
                UserApp(
                    screenType = getScreenType(windowSize)
                )
            }
        }
    }
}
