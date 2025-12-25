package me.arun.vastu.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import me.arun.vastu.features.auth.login.presentation.LoginEvent
import me.arun.vastu.features.auth.login.presentation.LoginRoot
import me.arun.vastu.features.auth.register.presentation.RegisterEvent
import me.arun.vastu.features.auth.register.presentation.RegisterRoot
import me.arun.vastu.features.home.courses.presentation.CoursesEvent
import me.arun.vastu.features.home.courses.presentation.CoursesRoot
import me.arun.vastu.features.home.courses.screens.details.presentation.DetailsRoot
import me.arun.vastu.features.home.dashboard.presentation.DashboardEvent
import me.arun.vastu.features.home.dashboard.presentation.DashboardRoot
import me.arun.vastu.features.home.profile.presentation.ProfileEvent
import me.arun.vastu.features.home.profile.presentation.ProfileRoot
import me.arun.vastu.features.vedio.presentation.VideoPlayerRoot

val TOP_LEVEL_DESTINATIONS: Map<NavKey, AppScreen> = mapOf(
    AppScreen.Dashboard to AppScreen.Dashboard,
    AppScreen.Courses to AppScreen.Courses,
    AppScreen.Stats to AppScreen.Stats,
    AppScreen.Profile to AppScreen.Profile,
)

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    isLoggedIn: Boolean = false,
) {
    val navigationState = rememberNavigationState(
        startRoute = AppScreen.Dashboard,
        topLevelRoutes = TOP_LEVEL_DESTINATIONS.keys
    )
    val navigator = remember {
        Navigator(navigationState)
    }

    val currentScreen = navigationState.backStacks[navigationState.topLevelRoute]?.lastOrNull()

    val showBottomBar = currentScreen !is ProtectedRoute.Lecture

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomBarItems.forEach { item ->
                         val isSelected = item.route == navigationState.topLevelRoute
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = { navigator.navigate(item.route, isLoggedIn) },
                            icon = { Icon(item.icon, contentDescription = item.title) },
                            label = { Text(item.title) }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavDisplay(
            modifier = Modifier.padding(paddingValues),
            onBack = navigator::goBack,
            entries = navigationState.toEntries(
                entryProvider = entryProvider {
                    entry<AppScreen.Dashboard> {
                        DashboardRoot {event ->
                            when(event){
                                is DashboardEvent.NavigateToCourse -> navigator.navigate(AppScreen.CourseDetails(event.courseId))
                                DashboardEvent.NavigateToProfile -> navigator.navigate(ProtectedRoute.MyCourses, isLoggedIn)
                                is DashboardEvent.NavigateToVideoPlayer -> navigator.navigate(ProtectedRoute.Lecture(event.courseId, "1"), isLoggedIn)
                            }

                        }
                    }
                    entry<AppScreen.Courses> {
                        CoursesRoot {event ->
                            when(event){
                                is CoursesEvent.NavigateToCourseDetails -> navigator.navigate(AppScreen.CourseDetails(event.courseId))
                            }
                        }
                    }
                    entry<AppScreen.CourseDetails> {
                        DetailsRoot(it.courseId) {event ->
                            when(event){
                                else -> {}
                            }
                        }
                    }
                    entry<AppScreen.Stats> {
                    }
                    entry<AppScreen.Profile> {
                        ProfileRoot { event ->
                            when (event) {
                                ProfileEvent.Logout -> {
                                    navigator.logout()
                                    navigator.navigate(AppScreen.Login(redirectTo = AppScreen.Dashboard))
                                }
                                else -> {}
                            }
                        }
                    }
                    entry<AppScreen.Login> {
                        LoginRoot {event ->
                            when(event){
                                LoginEvent.NavigateToHome -> {
                                    val redirectTo = (it).redirectTo
                                    if (redirectTo != null) {
                                        navigator.navigate(redirectTo, true)
                                    } else {
                                        navigator.navigate(AppScreen.Dashboard)
                                    }
                                }
                                LoginEvent.NavigateToRegister -> navigator.navigate(AppScreen.Register)
                            }
                        }
                    }
                    entry<AppScreen.Register> {
                        RegisterRoot {event ->
                            when(event){
                                RegisterEvent.NavigateToHome -> navigator.navigate(AppScreen.Dashboard)
                                RegisterEvent.NavigateToLogin -> navigator.navigate(AppScreen.Login(redirectTo = AppScreen.Dashboard))
                            }
                        }
                    }
                    entry<ProtectedRoute.MyCourses> {
                        // This would be your MyCourses screen
                        // PlaceholderScreen("My Courses")
                    }
                    entry<ProtectedRoute.Lecture> {
                        val key = it
                        VideoPlayerRoot(
                            courseId = key.courseId,
                            lastWatchedPositionMillis = 0L, // ViewModel should handle this
                            onEvent = {}
                        )
                    }
                    entry<ProtectedRoute.Enroll> {
                        // This would be your enroll screen
                        // PlaceholderScreen("Enroll in course ${it.key.courseId}")
                    }
                }
            ),
        )
    }
}
