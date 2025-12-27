package me.arun.vastu.features.home.courses.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.arun.vastu.features.home.courses.presentation.CoursesAction
import me.arun.vastu.features.home.courses.presentation.CoursesState
import me.arun.vastu.features.home.courses.presentation.CoursesTab

@Composable
fun CoursesContent(
    state: CoursesState,
    onAction: (CoursesAction) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        CourseTabs(
            selected = state.selectedTab,
            onSelect = { onAction(CoursesAction.OnTabChange(it)) }
        )

        val list = if (state.selectedTab == CoursesTab.All) state.allCourses else state.enrolledCourses

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            items(list) { course ->
                CoursesCard(
                    course = course,
                    onClick = { onAction(CoursesAction.OnCourseClick(course.id)) }
                )
            }
        }
    }
}
