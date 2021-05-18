package co.id.kadaluarsa.navigation


object DeeplinkUri {
    const val userDetailPage = "example://userdetail/args"
}

sealed class DeepLinkDestination(val address: String) {
    class UserDetail(username : String) : DeepLinkDestination("${DeeplinkUri.userDetailPage}?username=${username}")
    object Next : DeepLinkDestination("example://next")
}