package com.brosedda.mymediary.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.brosedda.mymediary.R

val Metamorphous = FontFamily(
    Font(R.font.metamorphous_regular)
)

val Baskerville = FontFamily(
    Font(R.font.libre_baskerville_regular),
    Font(R.font.libre_baskerville_bold, FontWeight.Bold),
    Font(R.font.libre_baskerville_italic, FontWeight.Normal, FontStyle.Italic)
)


// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Metamorphous,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Metamorphous,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Baskerville,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Baskerville,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)