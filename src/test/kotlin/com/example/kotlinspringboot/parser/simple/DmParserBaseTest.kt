package com.example.kotlinspringboot.parser.simple

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class DmParserBaseTest {
    @Test
    internal fun `add all`() {
        var left = mutableListOf("A", "b")
        left = DmParserBase().mergeToLeft(left, mutableListOf("c", "d"))
        println(left)
    }

    @Test
    internal fun `combine test`() {
        var left = mutableListOf("A", "b")
        left = DmParserBase().getCombinationsOfTwoList(left, mutableListOf("", ""), null)
        println(left)
    }

    @ParameterizedTest
    @CsvSource(
            "abc, abc",
            "'abc d', 'abc d'",
            "a|%any%|b, +a +b",
            "a b|%precededBy%|b, +(a b) +b",
            "a|%precededBy%|, +a",
            "a|%any%|, +a",
            "a|%precededBy%|'b c'|%precededBy%|, +a +('b c')"
    )
    internal fun `handle parts test`(ins: String, outs: String) {
        assertThat(DmParserBase().handleParts(ins.split("|").toList(), mutableListOf())).isEqualTo(outs)
    }

    @ParameterizedTest
    @CsvSource(
            "%any% a, %any% a",
            "%false% a, %false% a",
            "%precededBy% a, %precededBy% a",
            "a {%#\\d[3_7]%}, a",
            "{%+MatchCase% PIN | PINs | PINS %-MatchCase%}, { PIN | PINs | PINS }",
            "%!-- Password --% PW | PWs | PWD | PWDs %PrecededBy% %WhiteSpace%, PW | PWs | PWD | PWDs %PrecededBy%",
            "%-matchpunct% password* %any%[5] {non-public} | {non public} information, " +
                    "password* %any%[5] {non-public} | {non public} information",
            "a %other% b, a b"
    )
    internal fun `preHandling test`(ins: String, outs: String) {
        assertThat(preHandling(ins)).isEqualTo(outs)
    }


}

