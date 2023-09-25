package com.mcmouse88.testing_on_android

import com.google.common.truth.Truth
import org.junit.Test

class RegistrationUtilsTest {

    @Test
    fun `empty username returns false`() {
        val result = RegistrationUtils.validateRegistrationInput(
            username = "",
            password = "123",
            confirmPassword = "123",
        )
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password returns true`() {
        val result = RegistrationUtils.validateRegistrationInput(
            username = "user",
            password = "123",
            confirmPassword = "123",
        )
        Truth.assertThat(result).isTrue()
    }

    @Test
    fun `username already exists returns false`() {
        val result = RegistrationUtils.validateRegistrationInput(
            username = "Carl",
            password = "123",
            confirmPassword = "123",
        )
        Truth.assertThat(result).isFalse()
    }

    // empty password
    // password was repeated incorrectly
    // password contains less two digits

    @Test
    fun `empty password returns false`() {
        val result = RegistrationUtils.validateRegistrationInput(
            username = "user",
            password = "",
            confirmPassword = "",
        )
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `password has been repeated incorrectly returns false`() {
        val result = RegistrationUtils.validateRegistrationInput(
            username = "user",
            password = "123",
            confirmPassword = "321",
        )
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `password contains less than two digit returns false`() {
        val result = RegistrationUtils.validateRegistrationInput(
            username = "user",
            password = "abc1",
            confirmPassword = "abc1",
        )
        Truth.assertThat(result).isFalse()
    }
}