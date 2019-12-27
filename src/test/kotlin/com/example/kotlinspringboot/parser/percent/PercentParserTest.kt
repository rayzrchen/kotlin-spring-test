package com.example.kotlinspringboot.parser.percent

import org.junit.jupiter.api.Test

internal class PercentParserTest {
    @Test
    internal fun `parse tests`() {
//        PercentParser.substitute2("a b")
//        PercentParser.substitute2("a{b}c")
//        PercentParser.substitute2("{a b}")
        PercentParser.substitute2("a {b {c}}")
//        PercentParser.substitute2("{a b {c{d}}} e")
    }
}
