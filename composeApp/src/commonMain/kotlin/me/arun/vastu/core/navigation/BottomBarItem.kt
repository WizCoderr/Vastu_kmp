package me.arun.vastu.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.StackedBarChart
import androidx.compose.ui.graphics.vector.ImageVector
import me.arun.vastu.core.components.icons.VastuIcons

data class BottomBarItem(
    val title: String,
    val icon: ImageVector,
    val route: AppScreen
)

val bottomBarItems = listOf(
    BottomBarItem(
        title = "Dashboard",
        icon = VastuIcons.dashboard,
        route = AppScreen.Dashboard
    ),
    BottomBarItem(
        title = "Courses",
        icon = VastuIcons.course,
        route = AppScreen.Courses
    ),
    BottomBarItem(
        title = "Profile",
        icon = VastuIcons.Profile,
        route = AppScreen.Profile
    )
)
