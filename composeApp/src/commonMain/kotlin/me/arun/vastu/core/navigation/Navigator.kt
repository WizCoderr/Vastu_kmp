package me.arun.vastu.core.navigation

class Navigator(val state: NavigationState) {

    fun navigate(route: AppScreen, isLoggedIn: Boolean = true) {
        if (route is ProtectedRoute && !isLoggedIn) {
            navigate(AppScreen.Login(redirectTo = route), isLoggedIn = true)
            return
        }

        if (route in state.backStacks.keys) {
            state.topLevelRoute = route
        } else {
            state.backStacks[state.topLevelRoute]?.add(route)
        }
    }

    fun goBack() {
        val currentStack = state.backStacks[state.topLevelRoute]
            ?: error("Back stack for ${state.topLevelRoute} doesn't exist")

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