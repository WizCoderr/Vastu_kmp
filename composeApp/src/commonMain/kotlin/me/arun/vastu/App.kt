package me.arun.vastu

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.arun.vastu.core.theme.VastuMobileTheme
import me.arun.vastu.core.navigation.NavigationRoot

@Composable
fun App(
    modifier: Modifier = Modifier,
) {
VastuMobileTheme {
        NavigationRoot()
    }
}