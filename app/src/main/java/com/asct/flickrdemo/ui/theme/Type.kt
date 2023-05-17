package com.asct.flickrdemo.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.asct.flickrdemo.R

// Set of Material typography styles to start with

private val fonts = FontFamily(
    Font(R.font.sofia_pro_black, weight = FontWeight.Black),
    Font(R.font.sofia_pro_bold, weight = FontWeight.Bold),
    Font(R.font.sofia_pro_light, weight = FontWeight.Light),
    Font(R.font.sofia_pro_medium, weight = FontWeight.Medium),
    Font(R.font.sofia_pro_medium_it, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(R.font.sofia_pro_regular, weight = FontWeight.Normal),
    Font(R.font.sofia_pro_semi_bold, weight = FontWeight.SemiBold)
)

val Typography = Typography(
    defaultFontFamily = fonts
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)