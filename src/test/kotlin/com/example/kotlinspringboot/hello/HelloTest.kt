package com.example.kotlinspringboot.hello

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class HelloTest {
    @ParameterizedTest
    @CsvSource(
            "abc 11 22 cc \t a",
            delimiter = '\t'
    )
    internal fun `parser tests`(input: String, output: String) {
        Hello.parse(input)
    }
}
