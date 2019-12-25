package com.example.kotlinspringboot.simple

import com.example.kotlinspringboot.simple.Simple1.Input
import org.junit.jupiter.api.Test
import java.io.StringReader

internal class Simple1Test {

    @Test
    internal fun test1() {
        val simple1 = Simple1(StringReader("{}{"))
        Input()
    }
}
