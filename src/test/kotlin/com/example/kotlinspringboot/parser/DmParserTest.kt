package com.example.kotlinspringboot.parser

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class DmParserTest {
    @ParameterizedTest
    @CsvSource(
            "aaa|bb*, 'aaa' 'bb*' ",
            "a|b|c, 'a' 'b' 'c'",
            "{a|b|c}, 'a' 'b' 'c'",
            "{a b}|{c d}, 'a b' 'c d'",
            "{a b}|{c|d}, 'a b' 'c' 'd'",
            "a b|c, 'a b' 'a c'",
            "a?A b|c, 'a?A b' 'a?A c'",
            "a b|{c d}, 'a b' 'a c d'",
            "a b|{c|d}, 'a b' 'a c' 'a d'",
            "this is a long time|{history book}, 'this is a long time' 'this is a long history book'",
            "a b|{c {d|e|f}}, 'a b' 'a c d' 'a c e' 'a c f'",
            "a b|c|{d k} g|h, 'a b g' 'a c g' 'a d k g' 'a b h' 'a c h' 'a d k h'"
            )
    internal fun `parse test`(inStr: String, outStr: String) {
        val list = DmParser.substitute(inStr)
        val actual = list.joinToString(" ") { "'$it'" }
        assertThat(actual).isEqualTo(outStr)

    }

    @Test
    internal fun results() {
        "aa|bb|{cc|dd kk} ee %ANY% fff|gg ==> {aa|bb|{cc|dd kk} ee} {fff|gg}"
        "aa|bb|{cc|dd kk} ee {%FALSE%} fff|gg ==> {aa|bb|{cc|dd kk} ee} {fff|gg}"
        "aa|bb|{cc|dd kk} ee {abc|d}%FALSE% fff|gg ==> {aa|bb|{cc|dd kk} ee} {fff|gg}"
        "aa|bb|{cc|dd kk} ee aa%FALSE% fff|gg ==> {aa|bb|{cc|dd kk} ee} {fff|gg}"


    }
}

