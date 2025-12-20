package me.arun.vastu.features.home.courses.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.arun.vastu.features.home.courses.components.CoursesContent
import org.koin.compose.viewmodel.koinViewModel

/**
 * Composable entry point for the Courses feature.
 */
@Composable
fun CoursesRoot(
    viewModel: CoursesViewModel = koinViewModel(),
    onEvent: (CoursesEvent) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadInitialData()
    }

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            onEvent(event)
        }
    }

    CoursesScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

/**
 * A stateless composable that draws the UI for the Courses feature.
 */
@Composable
private fun CoursesScreen(
    state: CoursesState,
    onAction: (CoursesAction) -> Unit
) {
    when {
        state.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.error,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        state.courses.isEmpty() -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No courses available",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        else -> {
            CoursesContent(
                courses = state.courses,
                onCourseClick = { courseId ->
                    onAction(CoursesAction.OnCourseClick(courseId))
                }
            )
        }
    }
}
