package com.app.copamundialfifa2026.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary            = PaniniRed,
    onPrimary          = Color.White,
    primaryContainer   = Color(0xFFFFDAD8),
    onPrimaryContainer = Color(0xFF410009),
    secondary          = PaniniGold,
    onSecondary        = PaniniInk,
    secondaryContainer = Color(0xFFFFF0CC),
    onSecondaryContainer = PaniniInk,
    tertiary           = PaniniTeal,
    onTertiary         = Color.White,
    tertiaryContainer  = Color(0xFFB2EFEA),
    onTertiaryContainer = Color(0xFF002020),
    background         = PaniniSurface,
    onBackground       = PaniniInk,
    surface            = PaniniSoft,
    onSurface          = PaniniInk,
    surfaceVariant     = Color(0xFFEDE7DA),
    onSurfaceVariant   = Color(0xFF4A4540),
    outline            = Color(0xFFAFA89E),
    error              = Color(0xFFBA1A1A),
    onError            = Color.White
)

private val DarkColors = darkColorScheme(
    primary            = PaniniRedOnDark,
    onPrimary          = Color(0xFF690012),
    primaryContainer   = Color(0xFF93001A),
    onPrimaryContainer = Color(0xFFFFDAD8),
    secondary          = PaniniGoldOnDark,
    onSecondary        = Color(0xFF3A2800),
    secondaryContainer = Color(0xFF553D00),
    onSecondaryContainer = Color(0xFFFFDFA0),
    tertiary           = PaniniTealOnDark,
    onTertiary         = Color(0xFF003735),
    tertiaryContainer  = Color(0xFF00504D),
    onTertiaryContainer = Color(0xFFB2EFEA),
    background         = Dark900,
    onBackground       = Color(0xFFE8E2D9),
    surface            = Dark800,
    onSurface          = Color(0xFFE8E2D9),
    surfaceVariant     = Dark700,
    onSurfaceVariant   = Color(0xFFCFC8BC),
    outline            = Color(0xFF998F84),
    error              = Color(0xFFFF8A80),
    onError            = Color(0xFF690012),
    inverseSurface     = Color(0xFFE8E2D9),
    inverseOnSurface   = Dark800,
    inversePrimary     = PaniniRed
)

@Composable
fun PaniniSupportTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography  = Typography,
        content     = content
    )
}
