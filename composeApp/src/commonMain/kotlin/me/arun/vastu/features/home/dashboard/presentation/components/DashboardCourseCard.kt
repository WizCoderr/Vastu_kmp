package me.arun.vastu.features.home.dashboard.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import me.arun.vastu.features.home.dashboard.presentation.model.DashboardUiCourse

@Composable
fun DashboardCourseCard(
    course: DashboardUiCourse,
    onClick: () -> Unit,
    onContinue: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF121A2E)),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = course.thumbnail,
                    contentDescription = course.title,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(14.dp))
                )

                Spacer(Modifier.width(16.dp))
                Column {
                    AssistChip(
                        onClick = {},
                        label = { Text(course.tag) },
                        colors = AssistChipDefaults.assistChipColors(containerColor = Color(0xFF4B4DFF))
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(course.title, color = Color.White, fontSize = 18.sp)
                    Text(course.subtitle ?: "", color = Color.Gray, fontSize = 14.sp)
                }
            }

            Spacer(Modifier.height(12.dp))
            Text("Progress", color = Color.Gray, fontSize = 14.sp)
            LinearProgressIndicator(
                progress = { course.progress / 100f },
                trackColor = Color.DarkGray,
                color = if (course.isCompleted) Color(0xFF49D26D) else Color(0xFF6A75FF),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(Modifier.height(12.dp))

            if (course.isCompleted) {
                CompletedButton()
            } else {
                ContinueButton(onClick = onContinue)
            }
        }
    }
}

@Composable
private fun CompletedButton() =
    OutlinedButton(
        onClick = {},
        border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) { Text("Completed", color = Color(0xFF49D26D)) }

@Composable
private fun ContinueButton(onClick: () -> Unit) =
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6B72FF))
    ) { Text("Continue Learning") }
