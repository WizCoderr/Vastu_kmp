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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import me.arun.vastu.features.auth.login.presentation.LoginEvent
import me.arun.vastu.features.auth.login.presentation.LoginRoot
import me.arun.vastu.features.auth.register.presentation.RegisterEvent
import me.arun.vastu.features.auth.register.presentation.RegisterRoot
import me.arun.vastu.features.home.courses.presentation.CoursesEvent
import me.arun.vastu.features.home.courses.presentation.CoursesRoot
import me.arun.vastu.features.home.courses.screens.details.presentation.DetailsRoot
import me.arun.vastu.features.home.dashboard.presentation.DashboardEvent
import me.arun.vastu.features.home.dashboard.presentation.DashboardRoot

// --- This would be in your DI setup and injected ---
// Placeholder for authentication state
class AuthViewModel {
    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

    fun login() {
        _isUserLoggedIn.value = true
    }

    fun logout() {
        _isUserLoggedIn.value = false
    }
}

val TOP_LEVEL_DESTINATIONS: Map<NavKey, AppScreen> = mapOf(
    AppScreen.Dashboard to AppScreen.Dashboard,
    AppScreen.Courses to AppScreen.Courses,
    AppScreen.Stats to AppScreen.Stats,
)

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
) {
    val navigationState = rememberNavigationState(
        startRoute = AppScreen.Dashboard,
        topLevelRoutes = TOP_LEVEL_DESTINATIONS.keys
    )
    val navigator = remember {
        Navigator(navigationState)
    }
    val currentScreen = navigationState.backStacks[navigationState.topLevelRoute]?.lastOrNull()

    val showBottomBar = currentScreen !is AppScreen.Lecture

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomBarItems.forEach { item ->
                         val isSelected = item.route == navigationState.topLevelRoute
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = { navigator.navigate(item.route) },
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
                                DashboardEvent.NavigateToProfile -> navigator.navigate(AppScreen.Stats)
                                 is DashboardEvent.NavigateToVideoPlayer -> TODO()
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
                        DetailsRoot {event ->
                            when(event){
                                else -> {}
                            }
                        }
                    }
                    entry<AppScreen.Stats> {
                    }
                    entry<AppScreen.Login> {
                        LoginRoot {event ->
                            when(event){
                                LoginEvent.NavigateToHome -> navigator.navigate(AppScreen.Dashboard)
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
                    entry<AppScreen.MyCourses> {

                    }
                    entry<AppScreen.Enroll> {
                    }
                }
            ),
        )
    }
}
