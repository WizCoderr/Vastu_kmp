package me.arun.vastu

import androidx.compose.runtime.Composable
import me.arun.vastu.core.navigation.NavigationRoot
import me.arun.vastu.core.theme.VastuMobileTheme

@Composable
fun App() {
VastuMobileTheme {
        NavigationRoot()
    }
}