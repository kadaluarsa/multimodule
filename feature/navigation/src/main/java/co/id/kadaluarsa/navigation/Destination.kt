package co.id.kadaluarsa.navigation


sealed class DeepLinkDestination(val address: String) {
    class Dashboard(msg: String) : DeepLinkDestination("example://dashboard/exampleArgs?msg=${msg}")
    object Next : DeepLinkDestination("example://next")
}