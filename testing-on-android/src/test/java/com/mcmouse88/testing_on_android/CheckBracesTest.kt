package com.mcmouse88.testing_on_android

import com.google.common.truth.Truth
import org.junit.Test

class CheckBracesTest {

    @Test
    fun `when braces is close should return true`() {
        val actual = CheckBraces.checkBraces("(a + b)")
        Truth.assertThat(actual).isTrue()
    }

    @Test
    fun `when braces isn't close should return false`() {
        val actual = CheckBraces.checkBraces("(a + b))")
        Truth.assertThat(actual).isFalse()
    }
}