package com.example.a214185_nazatulaini_project2a.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val AppTypography = Typography(
    displayMedium = TextStyle(
        fontWeight = FontWeight.ExtraBold,
        fontSize   = 22.sp
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize   = 18.sp
    ),
    labelSmall = TextStyle(
        fontWeight    = FontWeight.Bold,
        fontSize      = 11.sp,
        letterSpacing = 2.sp
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize   = 14.sp
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize   = 14.sp
    )
)