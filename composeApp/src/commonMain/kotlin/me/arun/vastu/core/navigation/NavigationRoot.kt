package me.arun.vastu.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import kotlinx.serialization.Serializable
import me.arun.vastu.features.auth.navigation.AuthNavigation
import me.arun.vastu.features.home.navigation.HomeNavigationRoot
import me.arun.vastu.features.splash.SplashScreen


@Serializable
sealed interface MainNavigation : NavKey {
    @Serializable
    data object Splash : MainNavigation
    @Serializable
    data object Auth : MainNavigation

    @Serializable
    data object Home : MainNavigation

    @Serializable
    data object Settings : MainNavigation
}

val TOP_LEVEL_DESTINATIONS: Map<NavKey, MainNavigation> = mapOf(
    MainNavigation.Splash to MainNavigation.Splash,
    MainNavigation.Home to MainNavigation.Home,
    MainNavigation.Settings to MainNavigation.Settings,
)


@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
) {
    val navigationState = rememberNavigationState(
        startRoute = MainNavigation.Splash,
        topLevelRoutes = TOP_LEVEL_DESTINATIONS.keys
    )
    val navigator = remember {
        Navigator(navigationState)
    }
    NavDisplay(
        modifier = modifier,
        onBack = navigator::goBack,
        entries = navigationState.toEntries(
            entryProvider = entryProvider {
                entry<MainNavigation.Splash> {
                    SplashScreen(
                        navigateToHome = {
                            navigator.navigate(MainNavigation.Home)
                        },
                        navigateToAuth = {
                            navigator.navigate(MainNavigation.Auth)
                        }
                    )
                }
                entry<MainNavigation.Auth> {
                    AuthNavigation(
                        navigateToHome = {
                            navigator.navigate(MainNavigation.Home)
                        }
                    )
                }
                entry<MainNavigation.Home> {
                    HomeNavigationRoot()
                }
                entry<MainNavigation.Settings> {

                }
            }
        ),
    )

}