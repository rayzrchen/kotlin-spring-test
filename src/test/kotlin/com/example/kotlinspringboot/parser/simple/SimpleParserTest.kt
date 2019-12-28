package com.example.kotlinspringboot.parser.simple

import com.example.kotlinspringboot.parser.preHandling
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class SimpleParserTest {
    @ParameterizedTest
    @ValueSource(strings = [
        "aaa|bb*, aaa bb*",
        "a|b|c, a b c",
        "{a|b|c}, a b c",
        "{a b}|{c d}, 'a b' 'c d'",
        "{a b}|{c|d}, 'a b' c d",
        "a b|c, 'a b' 'a c'",
        "a?A b|c, 'a?A b' 'a?A c'",
        "a b|{c d}, 'a b' 'a c d'",
        "a b|{c|d}, 'a b' 'a c' 'a d'",
        "this is a long time|{history book}, 'this is a long time' 'this is a long history book'",
        "a b|{c {d|e|f}}, 'a b' 'a c d' 'a c e' 'a c f'",
        "a b|c|{d k} g|h, 'a b g' 'a b h' 'a c g' 'a c h' 'a d k g' 'a d k h'",
        "旺角 角b|c|{d k} g|h, '旺角 角b g' '旺角 角b h' '旺角 c g' '旺角 c h' '旺角 d k g' '旺角 d k h'",
        "{角|能}a, 角a 能a",
        "a{角|能}b, a角b a能b",
        "a{bb|cc}dd, abbdd accdd",
        "Sign On|Ons|In {ID|IDs}[?], 'Sign On' 'Sign Ons' 'Sign In'",
        "Per|Pers {Identification|{I.D.}|ID} Number|Nos|#, " +
                "'Per Identification Number' 'Per Identification Nos' 'Per Identification #' 'Per I.D. Number' 'Per I.D. Nos' 'Per I.D. #' 'Per ID Number' 'Per ID Nos' 'Per ID #' 'Pers Identification Number' 'Pers Identification Nos' 'Pers Identification #' 'Pers I.D. Number' 'Pers I.D. Nos' 'Pers I.D. #' 'Pers ID Number' 'Pers ID Nos' 'Pers ID #'",
        "旺角{角|能}局 , 旺角角局 旺角能局"
    ])
    internal fun `parse tests`(str: String) {
        val split = str.split("""\s*,\s*""".toRegex())
        assertThat(SimpleParser.simpleParse(preHandling(split[0]))).isEqualTo(split[1])
    }
}
