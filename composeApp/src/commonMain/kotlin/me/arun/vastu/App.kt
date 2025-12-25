package me.arun.vastu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import me.arun.vastu.core.navigation.NavigationRoot
import me.arun.vastu.core.theme.VastuMobileTheme
import me.arun.vastu.features.auth.viewmodel.AuthViewModel
import me.arun.vastu.features.auth.viewmodel.LoginScreenState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    val authViewModel: AuthViewModel = koinViewModel()
    val loginState by authViewModel.loginState.collectAsState()
    val isLoggedIn = loginState is LoginScreenState.Success

    VastuMobileTheme {
        NavigationRoot(
            isLoggedIn = isLoggedIn,
            onLoginSuccess = {} // The login logic is handled in the ViewModel, this is just for state propagation
        )
    }
}