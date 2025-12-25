package me.arun.vastu.features.home.dashboard.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.arun.vastu.core.theme.DesignToken
import me.arun.vastu.core.theme.VastuMobileTheme
import me.arun.vastu.features.home.dashboard.presentation.components.DashboardCourseCard
import me.arun.vastu.features.home.dashboard.presentation.components.DashboardHeader
import me.arun.vastu.features.home.dashboard.presentation.model.DashboardUiCourse
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

/**
 * Composable entry point for the Dashboard feature.
 */
@Composable
fun DashboardRoot(
    viewModel: DashboardViewModel = koinViewModel(),
    onEvent: (DashboardEvent) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            onEvent(event)
        }
    }

    DashboardScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

/**
 * A stateless composable that draws the UI for the Dashboard feature.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashboardScreen(
    state: DashboardState,
    onAction: (DashboardAction) -> Unit
) {
    PullToRefreshBox(
        isRefreshing = state.isLoading,
        onRefresh = { onAction(DashboardAction.OnRefresh) }
    ) {
        when {
            state.isLoading && state.enrolledCourses.isEmpty() -> {
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
                    Text(text = state.error)
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(DesignToken.padding.large),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    item {
                        DashboardHeader(
                            name = state.userName,
                            totalActive = state.activeCoursesCount,
                            onProfileClick = { onAction(DashboardAction.OnProfileClick) }
                        )
                    }

                    items(state.enrolledCourses, key = { it.id }) { course ->
                        DashboardCourseCard(
                            course = course,
                            onClick = { onAction(DashboardAction.OnCourseClick(course.id)) },
                            onContinue = {
                                onAction(DashboardAction.OnContinueLearning(course.id, course.lastWatchedPositionMillis))
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DashboardScreenPreview(
    modifier: Modifier = Modifier
) {
    VastuMobileTheme {
        val courses = listOf(
            DashboardUiCourse("1", "Vastu for Home", "Basics of vastu for home", "Vastu", 50, false, 0L, "",  ""),
            DashboardUiCourse("2", "Vastu for Office", "Basics of vastu for office", "Vastu", 20, false, 0L, "",""),
            DashboardUiCourse("3", "Numerology", "Basics of numerology", "Numerology", 80, false, 0L, "",""),
            DashboardUiCourse("4", "Astrology", "Basics of astrology", "Astrology", 100, true, 0L, "",""),
            DashboardUiCourse("5", "Palmistry", "Basics of palmistry", "Palmistry", 0, false, 0L, "","")
        )
        DashboardScreen(
            state = DashboardState(
                userName = "Arun",
                instituteName = "Vastu",
                activeCoursesCount = 4,
                enrolledCourses = courses
            ),
            onAction = {}
        )
    }
}