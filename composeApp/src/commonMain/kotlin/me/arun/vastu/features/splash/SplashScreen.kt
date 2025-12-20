package me.arun.vastu.features.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashScreen(
    navigateToHome: () -> Unit,
    navigateToAuth: () -> Unit,
    viewModel: SplashViewModel = koinViewModel()
) {
    val isUserAuthenticated by viewModel.isUserAuthenticated.collectAsState()

    LaunchedEffect(isUserAuthenticated) {
        when (isUserAuthenticated) {
            true -> navigateToHome()
            false -> navigateToAuth()
            null -> {
                // Do nothing, still loading
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
