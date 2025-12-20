package me.arun.vastu.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val primaryDark = Color(0xFF6366F1)
val onPrimaryDark = Color(0xFFFFFFFF)
val primaryContainerDark = Color(0xFF494CC3)
val onPrimaryContainerDark = Color(0xFFFFFFFF)
val secondaryDark = Color(0xFF1A1C1E)
val onSecondaryDark = Color(0xFFFFFFFF)
val secondaryContainerDark = Color(0xFF2F3235)
val onSecondaryContainerDark = Color(0xFFFFFFFF)
val tertiaryDark = Color(0xFF10B981)
val onTertiaryDark = Color(0xFFFFFFFF)
val tertiaryContainerDark = Color(0xFF0D9467)
val onTertiaryContainerDark = Color(0xFFFFFFFF)
val errorDark = Color(0xFFCF6679)
val onErrorDark = Color(0xFF000000)
val backgroundDark = Color(0xFF121212)
val onBackgroundDark = Color(0xFFFFFFFF)
val surfaceDark = Color(0xFF1A1C1E)
val onSurfaceDark = Color(0xFFFFFFFF)
val surfaceVariantDark = Color(0xFF2F3235)
val onSurfaceVariantDark = Color(0xFFFFFFFF)
val outlineDark = Color(0xFF8F9094)


private val DarkColorScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
)

@Composable
fun VastuMobileTheme(
    // Dynamic color is supported on Android 12+
    // But we are explicitly disabling it as per the user's request.
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = appTypography(),
        content = content
    )
}