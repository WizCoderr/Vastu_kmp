package me.arun.vastu.features.home.dashboard.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@Composable
fun DashboardHeader(
    name: String,
    totalActive: Int,
    onProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "INSTITUTE OF EXCELLENCE",
                color = Color.Gray,
                fontSize = 12.sp
            )

            AsyncImage(
                model = "https://i.pravatar.cc/150?img=3",
                contentDescription = null,
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .clickable { onProfileClick() }
            )
        }

        Spacer(Modifier.height(12.dp))

        Text("Welcome back,", fontSize = 26.sp, color = Color.White)
        Text("$name.", fontSize = 26.sp, color = Color(0xFF6D7AFE))

        Spacer(Modifier.height(6.dp))
        Text("Let's focus on your goals today.", color = Color.Gray, fontSize = 14.sp)

        Spacer(Modifier.height(20.dp))
        AssistChip(
            onClick = {},
            label = { Text("$totalActive Active") },
            colors = AssistChipDefaults.assistChipColors(containerColor = Color(0xFF4B4DFF))
        )
    }
}
