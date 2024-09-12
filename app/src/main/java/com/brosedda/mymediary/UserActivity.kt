package com.brosedda.mymediary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.brosedda.mymediary.ui.UserApp
import com.brosedda.mymediary.ui.theme.MyMediaRyTheme

class UserActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyMediaRyTheme(darkTheme = false) {
                UserApp()
            }
        }
    }
}
