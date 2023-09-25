package com.mcmouse88.testing_on_android

import com.google.common.truth.Truth
import org.junit.Test

class FibonacciTest {

    @Test
    fun `when param is equal 0 should returns 0`() {
        val expected = 0L
        val actual = Fibonacci.fib(0)
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when param is equal 1 should returns 1`() {
        val expected = 1L
        val actual = Fibonacci.fib(1)
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when param is equal 2 should returns 1`() {
        val expected = 1L
        val actual = Fibonacci.fib(2)
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when param is equal 3 should 2`() {
        val expected = 2L
        val actual = Fibonacci.fib(3)
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when param is equal 7 should 13`() {
        val expected = 13L
        val actual = Fibonacci.fib(7)
        Truth.assertThat(actual).isEqualTo(expected)
    }
}