package me.arun.vastu.features.home.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.arun.vastu.core.theme.DesignToken

@Composable
fun InfoCard(content: @Composable ColumnScope.() -> Unit) {
    Surface(
        shape = DesignToken.shapes.large,
        tonalElevation = DesignToken.spacing.tiny,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(DesignToken.padding.large), content = content)
    }
}
