package co.id.kadaluarsa.navigation

import co.id.kadaluarsa.navigation.NavigationFlow

interface ToFlowNavigatable {
    fun navigateToFlow(flow: NavigationFlow)
}