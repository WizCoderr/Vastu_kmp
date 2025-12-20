package me.arun.vastu.features.home.courses.screens.enrolled.presentation

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
 * Composable entry point for the Enrolled feature.
 */
@Composable
fun EnrolledRoot(
    viewModel: EnrolledViewModel = koinViewModel(),
    onEvent: (EnrolledEvent) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            onEvent(event)
        }
    }

    EnrolledScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

/**
 * A stateless composable that draws the UI for the Enrolled feature.
 */
@Composable
private fun EnrolledScreen(
    state: EnrolledState,
    onAction: (EnrolledAction) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Text(text = "Feature: Enrolled")
        }
    }
}
