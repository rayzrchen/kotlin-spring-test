package com.example.kotlinspringboot.flw

import org.junit.jupiter.api.Test

internal class FLWTest {
    @Test
    internal fun parseTest() {
        FLW.substitute("abcd abcde 1234 aaa## ")
    }
}
