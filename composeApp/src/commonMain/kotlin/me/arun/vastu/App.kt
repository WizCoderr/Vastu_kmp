package me.arun.vastu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import me.arun.vastu.core.navigation.AuthViewModel
import me.arun.vastu.core.navigation.NavigationRoot
import me.arun.vastu.core.theme.VastuMobileTheme

@Composable
fun App() {
    // In a real app, this would be injected by Koin at a higher level
    val authViewModel: AuthViewModel = remember { AuthViewModel() }
    val isLoggedIn by authViewModel.isUserLoggedIn.collectAsState()

    VastuMobileTheme {
        NavigationRoot(
            isLoggedIn = isLoggedIn,
            onLoginSuccess = { authViewModel.login() }
        )
    }
}