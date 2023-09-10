package com.mcmouse88.testing_on_android

object RegistrationUtils {

    private val existingUsers = listOf("Peter", "Carl")

    /**
     * the input isn't valid if:
     * - the username/password is empty
     * - the username has already taken
     * - the confirmed password isn't the same as real password
     * - the password contains less than 2 digits
     */
    fun validateRegistrationInput(
        username: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (username.isBlank() || password.isBlank()) return false
        if (username in existingUsers) return false
        if (password != confirmPassword) return false
        if (password.count { it.isDigit() } < 2) return false
        return true
    }
}