package co.id.kadaluarsa.github.usecase
sealed class Error {

    object NetworkError : Error()
    object GenericError : Error()
    object ResponseError : Error()
    object PersistenceError : Error()
}