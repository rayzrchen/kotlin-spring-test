package com.example.kotlinspringboot.parser.simple

import com.example.kotlinspringboot.parser.preHandling
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class SimpleParserBaseTest {
    @Test
    internal fun `add all`() {
        var left = mutableListOf("A", "b")
        left = SimpleParserBase().mergeToLeft(left, mutableListOf("c", "d"))
        println(left)
    }

    @Test
    internal fun `combine test`() {
        var left = mutableListOf("A", "b")
        left = SimpleParserBase().getCombinationsOfTwoList(left, mutableListOf("", ""), null)
        println(left)
    }

    @ParameterizedTest
    @ValueSource(
            strings = [
                "%any% a, %any% a",
                "%false% a, %false% a",
                "%precededBy% a, %precededBy% a",
                "a {%#\\d[3_7]%}, a",
                "{%+MatchCase% PIN | PINs | PINS %-MatchCase%}, { PIN | PINs | PINS }",
                "%!-- Password --% PW | PWs | PWD | PWDs %PrecededBy% %WhiteSpace%, PW | PWs | PWD | PWDs %PrecededBy%",
                "%-matchpunct% password* %any%[5] {non-public} | {non public} information, " +
                        "password* %any%[5] {non-public} | {non public} information",
                "a %other% b, a b"
            ]
    )
    internal fun `preHandling test`(values: String) {
        val split = values.split("""\s*,\s*""".toRegex())
        assertThat(preHandling(split[0])).isEqualTo(split[1])
    }


}

