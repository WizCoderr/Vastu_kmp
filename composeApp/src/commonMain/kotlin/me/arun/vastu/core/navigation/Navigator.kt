package me.arun.vastu.core.navigation

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.arun.vastu.domain.repository.AuthRepository
import me.arun.vastu.domain.repository.EnrolmentRepository

class Navigator(
    private val state: NavigationState,
    private val authRepository: AuthRepository,
    private val enrolmentRepository: EnrolmentRepository,
    private val json: Json,
) {

    private fun currentStack() = state.backStacks[state.topLevelRoute]
        ?: error("Back stack for ${state.topLevelRoute} doesn't exist")

    fun navigate(route: AppScreen) {
        if (route in state.backStacks.keys) {
            state.topLevelRoute = route
        } else {
            currentStack().add(route)
        }
    }

    fun navigateProtected(destination: AppScreen) {
        if (destination is ProtectedRoute && !authRepository.isUserLoggedIn()) {
            val redirectTo = json.encodeToString(destination)
            navigate(AppScreen.Login(redirectTo = redirectTo))
            return
        }

        if (destination is ProtectedRoute.VideoPlayer) {
            if (!enrolmentRepository.isEnrolled(destination.courseId)) {
                navigate(ProtectedRoute.Enroll(destination.courseId))
                return
            }
        }

        navigate(destination)
    }

    fun onAuthSuccess(redirectTo: String?) {
        val stack = currentStack()
        val top = stack.lastOrNull()
        if ((top is AppScreen.Login || top is AppScreen.Register) && stack.size > 1) {
            stack.removeLast()
        }

        val destination = redirectTo?.let { json.decodeFromString<AppScreen>(it) }
        if (destination != null) {
            navigateProtected(destination)
        } else {
            navigate(AppScreen.Dashboard)
        }
    }

    fun goBack() {
        val currentStack = currentStack()

        if (currentStack.size > 1) {
            currentStack.removeLastOrNull()
        }

        else if (state.topLevelRoute != state.startRoute) {
            state.topLevelRoute = state.startRoute
        }
    }

    fun logout() {
        // Clear all back stacks
        state.backStacks.values.forEach { stack ->
            stack.clear()
        }
        // Reset to the start route
        state.topLevelRoute = state.startRoute
    }
}