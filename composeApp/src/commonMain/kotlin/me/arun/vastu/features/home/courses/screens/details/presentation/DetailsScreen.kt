package me.arun.vastu.features.home.courses.screens.details.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

/**
 * Composable entry point for the Details feature.
 */
@Composable
fun DetailsRoot(
    viewModel: DetailsViewModel = koinViewModel(),
    onEvent: (DetailsEvent) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            onEvent(event)
        }
    }

    DetailsScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

/**
 * A stateless composable that draws the UI for the Details feature.
 */
@Composable
private fun DetailsScreen(
    state: DetailsState,
    onAction: (DetailsAction) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Text(text = "Feature: Details")
        }
    }
}
