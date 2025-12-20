package me.arun.vastu.features.home.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import me.arun.vastu.core.components.icons.VastuIcons
import org.jetbrains.compose.resources.StringResource
import vastu.composeapp.generated.resources.Res
import vastu.composeapp.generated.resources.feature_home_course
import vastu.composeapp.generated.resources.feature_home_dashboard
import vastu.composeapp.generated.resources.feature_home_profile

@Serializable
sealed interface HomeDestinations: NavKey{
    @Serializable data object Dashboard: HomeDestinations
    @Serializable data object Courses: HomeDestinations
    @Serializable data object Profile: HomeDestinations
    @Serializable data class VideoPlayer(val courseId: String, val lastWatchedPositionMillis: Long) : HomeDestinations
}

data class BottomNavDestinations(
    val icon: ImageVector,
    val label: StringResource
)

val HOME_TOP_LEVEL_DESTINATION: Map<NavKey, BottomNavDestinations> = mapOf(
    HomeDestinations.Dashboard to BottomNavDestinations(
            icon = VastuIcons.dashboard,
    label = Res.string.feature_home_dashboard
    ),
    HomeDestinations.Courses to BottomNavDestinations(
        icon = VastuIcons.course,
        label = Res.string.feature_home_course
    ),
    HomeDestinations.Profile to BottomNavDestinations(
        icon = VastuIcons.Profile,
        label = Res.string.feature_home_profile
    ),
)

