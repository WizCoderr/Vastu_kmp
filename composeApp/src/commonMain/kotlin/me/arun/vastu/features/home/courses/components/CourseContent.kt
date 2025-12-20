package me.arun.vastu.features.home.courses.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.arun.vastu.features.home.courses.presentation.model.CourseUiModel

@Composable
fun CoursesContent(
    courses: List<CourseUiModel>,
    onCourseClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = courses,
            key = { it.id }
        ) { course ->
            CourseCard(
                course = course,
                onClick = { onCourseClick(course.id) }
            )
        }
    }
}
