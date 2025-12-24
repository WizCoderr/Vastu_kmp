package me.arun.vastu.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

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
    // This would be injected by Koin
    val authViewModel: AuthViewModel = remember { AuthViewModel() }

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
                        PlaceholderScreen("Dashboard") {
                            // Example of a protected action
                            Button(onClick = {
                                navigator.navigateToProtected(AppScreen.MyCourses, authViewModel.isUserLoggedIn.value)
                            }) {
                                Text("Go to My Courses")
                            }
                        }
                    }
                    entry<AppScreen.Courses> {
                        PlaceholderScreen("Courses") {
                             Button(onClick = {
                                navigator.navigate(AppScreen.CourseDetails("1"))
                            }) {
                                Text("Go to Course Details")
                            }
                        }
                    }
                    entry<AppScreen.CourseDetails> {
                         PlaceholderScreen("Course Details for ID: ${it.key.courseId}") {
                            Button(onClick = {
                                navigator.navigateToProtected(AppScreen.Lecture(it.key.courseId, "1"), authViewModel.isUserLoggedIn.value)
                            }) {
                                Text("Watch Lecture")
                            }
                        }
                    }
                    entry<AppScreen.Stats> {
                        PlaceholderScreen("Stats")
                    }
                    entry<AppScreen.Login> {
                        val key = it.key
                        PlaceholderScreen("Login") {
                            Button(onClick = {
                                authViewModel.login()
                                // After login, navigate to the intended destination if it exists
                                val redirectTo = key.redirectTo
                                if (redirectTo != null) {
                                    navigator.navigate(redirectTo as AppScreen)
                                } else {
                                    // Fallback to Dashboard after login
                                    navigator.navigate(AppScreen.Dashboard)
                                }
                            }) {
                                Text("Log In")
                            }
                        }
                    }
                    entry<AppScreen.Register> {
                        PlaceholderScreen("Register")
                    }
                    entry<AppScreen.MyCourses> {
                        PlaceholderScreen("My Courses") {
                            Button(onClick = {
                                authViewModel.logout()
                                navigator.logout()
                            }) {
                                Text("Logout")
                            }
                        }
                    }
                    entry<AppScreen.Lecture> {
                        val key = it.key
                        PlaceholderScreen("Lecture ${key.lectureId} from Course ${key.courseId}")
                    }
                    entry<AppScreen.Enroll> {
                         PlaceholderScreen("Enroll in course ${it.key.courseId}")
                    }
                }
            ),
        )
    }
}

@Composable
fun PlaceholderScreen(text: String, content: @Composable () -> Unit = {}) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text)
            content()
        }
    }
}
