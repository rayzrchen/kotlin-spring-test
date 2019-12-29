package com.example.kotlinspringboot.add_quote

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class AddQuoteTests {
    @ParameterizedTest
    @ValueSource(strings = [
        """
            a,, a
        """,
        """
            a b*,, a b*
        """,
        """
            "a b",, "a b"
        """,
        """
            +("a b"),, +("a b")
        """,
        """
            +a +(b c),, +a +(b c)
        """,
        """
            +a +(b?st c),, +a +(b?st c)
        """,
        """
            +a +(best* c),, +a +(best* c)
        """,
        """
            +a +("my best*" c),, +a +("my best*" c)
        """,
        """
            +a +(震撼晶片 d),, +a +("震撼晶片" d)
        """
    ])
    internal fun `add quote tests`(value: String) {
        val split = value.split(",,").map { it.trim() }
        val actual = AddQuote().addQuoteForString(split[0])
        assertThat(actual).isEqualTo(split[1])
    }
}

