package me.arun.vastu.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val primaryLight = Color(0xFF6366F1)
val onPrimaryLight = Color(0xFFFFFFFF)
val primaryContainerLight = Color(0xFFE0E0FF)
val onPrimaryContainerLight = Color(0xFF1F1E80)
val secondaryLight = Color(0xFF5A5B71)
val onSecondaryLight = Color(0xFFFFFFFF)
val secondaryContainerLight = Color(0xFFE0E0E0)
val onSecondaryContainerLight = Color(0xFF1C1B2B)
val tertiaryLight = Color(0xFF10B981)
val onTertiaryLight = Color(0xFFFFFFFF)
val tertiaryContainerLight = Color(0xFFC3F3DA)
val onTertiaryContainerLight = Color(0xFF002111)
val errorLight = Color(0xFFB00020)
val onErrorLight = Color(0xFFFFFFFF)
val backgroundLight = Color(0xFFFFFFFF)
val onBackgroundLight = Color(0xFF000000)
val surfaceLight = Color(0xFFFFFFFF)
val onSurfaceLight = Color(0xFF000000)
val surfaceVariantLight = Color(0xFFE7E0EB)
val onSurfaceVariantLight = Color(0xFF49454E)
val outlineLight = Color(0xFF7A757F)

private val LightColorScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
)

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
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = appTypography(),
        content = content
    )
}