package com.blinkist.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = DeepJungleGreen,
    onPrimary = Color.White,
    secondary = DeepJungleGreen,
    onSecondary = Color.White,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black,
)
private val DarkColors = darkColorScheme(
    primary = DeepJungleGreen,
    onPrimary = Color.White,
    secondary = DeepJungleGreen,
    onSecondary = Color.White,
    background = Black,
    onBackground = White,
    surface = Black,
    surfaceTint = Black,
    onSurface = White,
)

@Composable
fun PlayMateTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content,
        typography = Typography,
    )
}
