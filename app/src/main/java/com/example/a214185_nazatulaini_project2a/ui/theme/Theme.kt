package com.example.a214185_nazatulaini_project2a.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

val AppShapes = Shapes(
    small  = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large  = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
)

@Composable
fun A214185_nazatulaini_lab05bTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // LightColorScheme and DarkColorScheme come from Color.kt
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography  = AppTypography,   // from Type.kt
        shapes      = AppShapes,
        content     = content
    )
}