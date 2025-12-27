package me.arun.vastu.features.home.navigation

import androidx.navigation3.runtime.NavKey

class HomeNavigator(val state: NavigationState) {

    fun navigate(route: NavKey) {
        if (route in state.backStacks.keys) {
            state.homeTopLevelRoute = route
        } else {
            state.backStacks[state.homeTopLevelRoute]?.add(route)
        }
    }

    fun goBack() {
        val currentStack = state.backStacks[state.homeTopLevelRoute]
            ?: error("Back stack for ${state.homeTopLevelRoute} doesn't exist")

        if (currentStack.size > 1) {
            currentStack.removeLastOrNull()
        } else if (state.homeTopLevelRoute != state.startRoute) {
            state.homeTopLevelRoute = state.startRoute
        }
    }
}