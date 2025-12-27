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
import kotlinx.serialization.json.Json
import me.arun.vastu.domain.repository.AuthRepository
import me.arun.vastu.domain.repository.EnrolmentRepository
import me.arun.vastu.features.auth.login.presentation.LoginEvent
import me.arun.vastu.features.auth.login.presentation.LoginRoot
import me.arun.vastu.features.auth.register.presentation.RegisterEvent
import me.arun.vastu.features.auth.register.presentation.RegisterRoot
import me.arun.vastu.features.home.courses.presentation.CoursesEvent
import me.arun.vastu.features.home.courses.presentation.CoursesRoot
import me.arun.vastu.features.home.courses.screens.details.presentation.DetailsEvent
import me.arun.vastu.features.home.courses.screens.details.presentation.DetailsRoot
import me.arun.vastu.features.home.courses.screens.enrolled.presentation.EnrolledRoot
import me.arun.vastu.features.home.dashboard.presentation.DashboardEvent
import me.arun.vastu.features.home.dashboard.presentation.DashboardRoot
import me.arun.vastu.features.vedio.presentation.VideoPlayerRoot
import org.koin.compose.koinInject

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

    val authRepository: AuthRepository = koinInject()
    val enrolmentRepository: EnrolmentRepository = koinInject()
    val json: Json = koinInject()

    val navigator = remember {
        Navigator(
            state = navigationState,
            authRepository = authRepository,
            enrolmentRepository = enrolmentRepository,
            json = json,
        )
    }

    val currentScreen = navigationState.backStacks[navigationState.topLevelRoute]?.lastOrNull()

    val showBottomBar = currentScreen !is ProtectedRoute.VideoPlayer

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
                                DashboardEvent.NavigateToProfile -> navigator.navigateProtected(ProtectedRoute.MyCourses)
                                is DashboardEvent.NavigateToVideoPlayer -> navigator.navigateProtected(
                                    ProtectedRoute.VideoPlayer(
                                        courseId = event.courseId,
                                        lastWatchedPositionMillis = event.lastWatchedPositionMillis,
                                    )
                                )
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
                                is DetailsEvent.NavigateToEnroll -> navigator.navigateProtected(ProtectedRoute.Enroll(event.courseId))
                                is DetailsEvent.NavigateToVideoPlayer -> navigator.navigateProtected(ProtectedRoute.VideoPlayer(event.courseId))
                            }
                        }
                    }
                    entry<AppScreen.Stats> {
                    }
                    entry<AppScreen.Login> {
                        LoginRoot {event ->
                            when(event){
                                LoginEvent.NavigateToHome -> {
                                    navigator.onAuthSuccess(it.redirectTo)
                                }
                                LoginEvent.NavigateToRegister -> navigator.navigate(AppScreen.Register(redirectTo = it.redirectTo))
                            }
                        }
                    }
                    entry<AppScreen.Register> {
                        RegisterRoot {event ->
                            when(event){
                                RegisterEvent.NavigateToHome -> navigator.onAuthSuccess(it.redirectTo)
                                RegisterEvent.NavigateToLogin -> navigator.navigate(AppScreen.Login(redirectTo = it.redirectTo))
                            }
                        }
                    }
                    entry<ProtectedRoute.MyCourses> {
                        EnrolledRoot(
                            onEvent = {
                            }
                        )
                    }
                    entry<ProtectedRoute.VideoPlayer> {
                        val key = it
                        VideoPlayerRoot(
                            courseId = key.courseId,
                            lastWatchedPositionMillis = key.lastWatchedPositionMillis,
                            onEvent = {}
                        )
                    }
                    entry<ProtectedRoute.Enroll> {
                        Text("Enroll for courseId: ${it.courseId}")
                    }
                }
            ),
        )
    }
}
