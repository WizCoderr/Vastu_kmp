@file:OptIn(ExperimentalMaterial3Api::class)

package me.arun.vastu.features.home.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import me.arun.vastu.core.components.icons.VastuIcons
import me.arun.vastu.features.home.courses.presentation.CoursesRoot
import me.arun.vastu.features.home.dashboard.presentation.DashboardRoot
import me.arun.vastu.features.home.dashboard.presentation.DashboardEvent
import me.arun.vastu.features.home.profile.presentation.ProfileEvent // Re-added import
import me.arun.vastu.features.vedio.presentation.VideoPlayerRoot
import me.arun.vastu.features.home.profile.presentation.ProfileRoot

@Composable
fun HomeNavigationRoot(
    modifier: Modifier = Modifier,
) {
    val homeNavState = rememberHomeNavigationState(
        startRoute = HomeDestinations.Dashboard,
        homeTopLevelRoutes = HOME_TOP_LEVEL_DESTINATION.keys
    )
    val navigator = remember {
        HomeNavigator(homeNavState)
    }

    Scaffold(
        topBar = {
            VastuTopAppBar(
                title = "Vastu",
                action = {
                    Icon(VastuIcons.Profile, contentDescription = "Profile",modifier = Modifier.clickable {
                        navigator.navigate(HomeDestinations.Profile)
                    })
                }
            )
        },
        bottomBar = {
            HomeBottomNav(
                selectedKey = homeNavState.homeTopLevelRoute,
                onSelectKey={
                    navigator.navigate(it)
                }
            )
        }
    ) {
        NavDisplay(
            modifier = modifier.padding(it),
            onBack = navigator::goBack,
            entries = homeNavState.toEntries(
                entryProvider = entryProvider {
                    entry<HomeDestinations.Dashboard> {
                        DashboardRoot(
                            onEvent = { event ->
                                when (event) {
                                    is DashboardEvent.NavigateToCourse -> { /* Handle as needed, e.g., navigate to course details */ }
                                    is DashboardEvent.NavigateToVideoPlayer -> {
                                        navigator.navigate(HomeDestinations.VideoPlayer(event.courseId, event.lastWatchedPositionMillis))
                                    }
                                    DashboardEvent.NavigateToProfile -> {
                                        navigator.navigate(HomeDestinations.Profile)
                                    }
                                }
                            }
                        )
                    }
                    entry<HomeDestinations.Courses> {
                        CoursesRoot(
                            onEvent = {}
                        )
                    }
                    entry<HomeDestinations.Profile> {
                        ProfileRoot(
                            onEvent = {it->
                                when (it) {
                                    ProfileEvent.NavigateToNotifications -> {}
                                    ProfileEvent.NavigateToPrivacy ->{}
                                    ProfileEvent.NavigateToTerms -> {}
                                    ProfileEvent.Logout -> {}
                                }
                            }
                        )
                    }
                    entry<HomeDestinations.VideoPlayer> { videoPlayer ->
                        VideoPlayerRoot(
                            courseId = videoPlayer.courseId,
                            lastWatchedPositionMillis = videoPlayer.lastWatchedPositionMillis,
                            onEvent = { /* Handle video player events */ }
                        )
                    }
                }
            ),
        )

    }
}