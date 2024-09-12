package com.brosedda.mymediary.tutorials.cupcake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.brosedda.mymediary.tutorials.cupcake.ui.CupcakeApp
import com.brosedda.mymediary.ui.theme.MyMediaRyTheme

class CupcakeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyMediaRyTheme {
                CupcakeApp()
            }
        }
    }
}