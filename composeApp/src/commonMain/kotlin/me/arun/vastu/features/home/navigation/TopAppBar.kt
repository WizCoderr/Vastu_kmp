@file:OptIn(ExperimentalMaterial3Api::class)

package me.arun.vastu.features.home.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun VastuTopAppBar(
    modifier: Modifier = Modifier,
    title:String,
    action: @Composable RowScope.() -> Unit
) {
    TopAppBar(
        title = { Text(title) },
        modifier = modifier.fillMaxWidth(),
        actions = action
    )
}