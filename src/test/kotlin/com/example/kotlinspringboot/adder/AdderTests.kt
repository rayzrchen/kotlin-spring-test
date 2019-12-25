package com.example.kotlinspringboot.adder

import org.junit.jupiter.api.Test

class AdderTests {
    @Test
    internal fun name() {
        println(Adder.parse(" 1 + 2 \n +  \r 3 + 4"))

    }
}
