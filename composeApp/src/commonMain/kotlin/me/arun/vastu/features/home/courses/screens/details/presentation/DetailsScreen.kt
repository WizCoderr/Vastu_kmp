package me.arun.vastu.features.home.courses.screens.details.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

import org.koin.core.parameter.parametersOf

/**
 * Composable entry point for the Details feature.
 */
@Composable
fun DetailsRoot(
    courseId: String,
    viewModel: DetailsViewModel = koinViewModel(parameters = { parametersOf(courseId) }),
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
        } else if (state.error != null) {
            Text(text = state.error)
        } else if (state.details != null) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = state.details.courseDetails.title, style = MaterialTheme.typography.headlineMedium)
                Text(text = state.details.courseDetails.description)
                Text(text = "Instructor: ${state.details.courseDetails.instructor}")
                Text(text = "Price: ${state.details.courseDetails.price}")
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Curriculum", style = MaterialTheme.typography.titleLarge)
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(state.details.curriculum) { chapter ->
                        Text(text = chapter.title, style = MaterialTheme.typography.titleMedium)
                        chapter.lessons.forEach { lesson ->
                            Text(text = "  - ${lesson.title}")
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { onAction(DetailsAction.OnEnrollClick) }) {
                    Text(text = "Enroll")
                }
            }
        }
    }
}
