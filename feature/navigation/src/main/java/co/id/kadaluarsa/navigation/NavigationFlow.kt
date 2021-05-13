package co.id.kadaluarsa.navigation

sealed class NavigationFlow {
    object SearchFlow : NavigationFlow()
    class UserFlow(val msg: String) : NavigationFlow()
}