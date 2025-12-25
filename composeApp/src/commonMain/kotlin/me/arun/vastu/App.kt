package me.arun.vastu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import me.arun.vastu.core.navigation.NavigationRoot
import me.arun.vastu.core.theme.VastuMobileTheme
import me.arun.vastu.features.splash.SplashViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun App() {
    val splashViewModel: SplashViewModel = koinViewModel()
    val isUserAuthenticated by splashViewModel.isUserAuthenticated.collectAsState()
    VastuMobileTheme {
        if (isUserAuthenticated != null) {
            NavigationRoot(isLoggedIn = isUserAuthenticated!!)
        }
    }
}