package co.id.kadaluarsa.navigation

import androidx.navigation.NavController


class Navigator {
    lateinit var navController: NavController

    // navigate on main thread or nav component crashes sometimes
    fun navigateToFlow(navigationFlow: NavigationFlow) = when (navigationFlow) {
        NavigationFlow.SearchFlow -> navController.navigate(MainNavGraphDirections.actionSearchFlow())
        is NavigationFlow.UserFlow -> navController.navigate(MainNavGraphDirections.actionUserFlow(navigationFlow.msg))
    }
}