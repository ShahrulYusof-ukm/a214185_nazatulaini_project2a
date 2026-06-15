package com.example.a214185_nazatulaini_project2a.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// ── Light Color Tokens ────────────────────────────────────────────────────────
val md_light_primary              = Color(0xFF9C0009)
val md_light_onPrimary            = Color(0xFFFFFFFF)
val md_light_primaryContainer     = Color(0xFFFFDAD7)
val md_light_onPrimaryContainer   = Color(0xFF400000)
val md_light_tertiary             = Color(0xFF755A2F)
val md_light_onTertiary           = Color(0xFFFFFFFF)
val md_light_tertiaryContainer    = Color(0xFFFFDEA8)
val md_light_onTertiaryContainer  = Color(0xFF281900)
val md_light_background           = Color(0xFFFFFBFF)
val md_light_onBackground         = Color(0xFF201A1A)
val md_light_surface              = Color(0xFFFFFBFF)
val md_light_onSurface            = Color(0xFF201A1A)
val md_light_surfaceVariant       = Color(0xFFF5DDDA)
val md_light_onSurfaceVariant     = Color(0xFF534340)
val md_light_outline              = Color(0xFF857370)

// ── Dark Color Tokens ─────────────────────────────────────────────────────────
val md_dark_primary               = Color(0xFFFFB4A9)
val md_dark_onPrimary             = Color(0xFF690003)
val md_dark_primaryContainer      = Color(0xFF930006)
val md_dark_onPrimaryContainer    = Color(0xFFFFDAD7)
val md_dark_tertiary              = Color(0xFFE5C18D)
val md_dark_onTertiary            = Color(0xFF422C05)
val md_dark_tertiaryContainer     = Color(0xFF5B421A)
val md_dark_onTertiaryContainer   = Color(0xFFFFDEA8)
val md_dark_background            = Color(0xFF201A1A)
val md_dark_onBackground          = Color(0xFFECE0DF)
val md_dark_surface               = Color(0xFF201A1A)
val md_dark_onSurface             = Color(0xFFECE0DF)
val md_dark_surfaceVariant        = Color(0xFF534340)
val md_dark_onSurfaceVariant      = Color(0xFFD8C2BE)
val md_dark_outline               = Color(0xFFA08C89)

val LightColorScheme = lightColorScheme(
    primary             = md_light_primary,
    onPrimary           = md_light_onPrimary,
    primaryContainer    = md_light_primaryContainer,
    onPrimaryContainer  = md_light_onPrimaryContainer,
    tertiary            = md_light_tertiary,
    onTertiary          = md_light_onTertiary,
    tertiaryContainer   = md_light_tertiaryContainer,
    onTertiaryContainer = md_light_onTertiaryContainer,
    background          = md_light_background,
    onBackground        = md_light_onBackground,
    surface             = md_light_surface,
    onSurface           = md_light_onSurface,
    surfaceVariant      = md_light_surfaceVariant,
    onSurfaceVariant    = md_light_onSurfaceVariant,
    outline             = md_light_outline
)

val DarkColorScheme = darkColorScheme(
    primary             = md_dark_primary,
    onPrimary           = md_dark_onPrimary,
    primaryContainer    = md_dark_primaryContainer,
    onPrimaryContainer  = md_dark_onPrimaryContainer,
    tertiary            = md_dark_tertiary,
    onTertiary          = md_dark_onTertiary,
    tertiaryContainer   = md_dark_tertiaryContainer,
    onTertiaryContainer = md_dark_onTertiaryContainer,
    background          = md_dark_background,
    onBackground        = md_dark_onBackground,
    surface             = md_dark_surface,
    onSurface           = md_dark_onSurface,
    surfaceVariant      = md_dark_surfaceVariant,
    onSurfaceVariant    = md_dark_onSurfaceVariant,
    outline             = md_dark_outline
)