package com.brosedda.mymediary.tutorials.unscramble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.brosedda.mymediary.tutorials.unscramble.ui.GameScreen
import com.brosedda.mymediary.ui.theme.MyMediaRyTheme

class UnscrambleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyMediaRyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val stopScreaming = innerPadding
                   GameScreen()
                }
            }
        }
    }
}
