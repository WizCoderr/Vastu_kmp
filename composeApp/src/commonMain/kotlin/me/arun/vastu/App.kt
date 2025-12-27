package me.arun.vastu


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import me.arun.vastu.core.navigation.NavigationRoot
import me.arun.vastu.core.theme.VastuMobileTheme
import me.arun.vastu.data.repository.AuthRepositoryImpl
import me.arun.vastu.domain.repository.AuthRepository
import me.arun.vastu.features.splash.SplashViewModel
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun App() {
    val splashViewModel: SplashViewModel = koinViewModel()
    val isUserAuthenticated by splashViewModel.isUserAuthenticated.collectAsState()
    VastuMobileTheme {
        if (isUserAuthenticated != null) {
            // This is a bridge to the dummy repository.
            // In a real app, the SplashViewModel and AuthRepository would likely use the same
            val authRepo = koinInject<AuthRepository>() as AuthRepositoryImpl
            authRepo.setLoggedIn(isUserAuthenticated!!)

            NavigationRoot()
        }
    }
}