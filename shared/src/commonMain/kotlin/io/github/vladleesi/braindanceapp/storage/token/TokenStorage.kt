package io.github.vladleesi.braindanceapp.storage.token

expect class TokenStorage constructor() {
    fun getToken(): String?
    fun saveToken(accessToken: String)
}
