package com.example.kotlinspringboot.my

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class MyParserTest {
    private val myParser = MyParser()

    @ParameterizedTest
    @CsvSource(
            "%!-- a b --% a \t a",
            "a|b \t a b",
            "a b|c \t \"a b\" \"a c\"",
            "a|b|c \t a b c",
            "a b|c d \t \"a b d\" \"a c d\"",
            "aa|cc|dd \t aa cc dd",
            delimiter = '\t'
    )
    fun testParser(input: String, outputString: String) {
        assertThat(myParser.parse(input)).isEqualTo(outputString)
    }
}

