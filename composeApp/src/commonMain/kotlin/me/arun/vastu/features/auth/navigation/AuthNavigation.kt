package me.arun.vastu.features.auth.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import me.arun.vastu.domain.usecase.auth.SaveAuthTokenUseCase
import me.arun.vastu.features.auth.login.presentation.LoginEvent
import me.arun.vastu.features.auth.login.presentation.LoginRoot
import me.arun.vastu.features.auth.register.presentation.RegisterEvent
import me.arun.vastu.features.auth.register.presentation.RegisterRoot
import org.koin.compose.koinInject

@Serializable
sealed interface AuthRoutes : NavKey {
    @Serializable
    data object AuthRegister : AuthRoutes

    @Serializable
    data object AuthLogin : AuthRoutes
}


@Composable
fun AuthNavigation(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    saveAuthTokenUseCase: SaveAuthTokenUseCase = koinInject()
) {
    val coroutineScope = rememberCoroutineScope()
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(AuthRoutes.AuthRegister::class, AuthRoutes.AuthRegister.serializer())
                    subclass(AuthRoutes.AuthLogin::class, AuthRoutes.AuthLogin.serializer())
                }
            }
        },
        AuthRoutes.AuthLogin
    )
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<AuthRoutes.AuthRegister> {
                RegisterRoot(
                    onEvent = { events ->
                        when (events) {
                            is RegisterEvent.NavigateToHome -> {
                                coroutineScope.launch {
                                    saveAuthTokenUseCase("dummy_token")
                                }
                                navigateToHome()
                            }

                            is RegisterEvent.NavigateToLogin -> {
                                backStack.add(AuthRoutes.AuthLogin)
                            }
                        }
                    }
                )
            }
            entry<AuthRoutes.AuthLogin> {
                LoginRoot(
                    onEvent = { events ->
                        when (events) {
                            is LoginEvent.NavigateToHome -> {
                                coroutineScope.launch {
                                    saveAuthTokenUseCase("dummy_token")
                                }
                                navigateToHome()
                            }

                            is LoginEvent.NavigateToRegister -> {
                                backStack.add(AuthRoutes.AuthRegister)
                            }
                        }
                    }
                )
            }
        }
    )
}
