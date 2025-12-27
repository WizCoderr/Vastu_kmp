package me.arun.vastu.features.home.courses.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.arun.vastu.features.home.courses.presentation.CoursesTab
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import vastu.composeapp.generated.resources.Res
import vastu.composeapp.generated.resources.feature_home_all_course
import vastu.composeapp.generated.resources.feature_home_enrolled_course

@Composable
fun CourseTabs(
    selected: CoursesTab,
    onSelect: (CoursesTab) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TabButton( Res.string.feature_home_all_course, selected == CoursesTab.All) { onSelect(CoursesTab.All) }
        TabButton(Res.string.feature_home_enrolled_course, selected == CoursesTab.Enrolled) { onSelect(CoursesTab.Enrolled) }
    }
}

@Composable
private fun TabButton(text: StringResource, selected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(25.dp))
            .background(if (selected) MaterialTheme.colorScheme.background else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            stringResource(text),
            color = if (selected) MaterialTheme.colorScheme.onBackground else Color.Gray
        )
    }
}
