package com.mcmouse88.testing_on_android

object CheckBraces {

    /**
     * Checks if the braces are set correctly
     * e.g. "(a * b))" should return false
     * case )a * b( actually returns true and this is wrong
     */
    fun checkBraces(str: String): Boolean {
        return str.count { it == '(' } == str.count { it == ')' }
    }
}