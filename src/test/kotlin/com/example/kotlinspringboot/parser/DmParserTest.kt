package com.example.kotlinspringboot.parser

import com.example.kotlinspringboot.parser.simple.DmParser
import com.example.kotlinspringboot.parser.simple.DmParserBase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class DmParserTest {
    @ParameterizedTest
    @CsvSource(
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
            )
    internal fun `parse test`(inStr: String, outStr: String) {
        val actual = DmParser.simple(inStr)
        assertThat(actual).isEqualTo(outStr)
    }


    @ParameterizedTest
    @CsvSource(
            "aaa|bb* %ANY% c, +(aaa bb*) +c",
            "aaa|bb* %ANY% %whitspace%, +(aaa bb*)",
            "aaa|bb* %ANY%[3] c, +(aaa bb*) +c",
            "{aaa c}|bb* %ANY%[aaa] c, +('aaa c' bb*) +c",
            "aaa|bb* whole[?] c, 'aaa c' 'bb* c'",
            "aaa|bb* d%FALSE% c, 'aaa c' 'bb* c' -d",
            "aaa|bb* c d%FALSE%, 'aaa c' 'bb* c' -d",
            "aa?a|b-b* c %ANY% ff|gg d%FALSE%, +('aa?a c' 'b-b* c') +(ff gg) -d",
            "aaa|bb* c {m|n o}%FALSE% d%FALSE%, 'aaa c' 'bb* c' -('m o' 'n o' d)",
            "aaa|bb* c {mo|n-o}%FALSE%, 'aaa c' 'bb* c' -(mo 'n-o')"
    )
    internal fun `parse test with function`(inStr: String, outStr: String) {
        val actual = DmParser.complex(inStr)
        assertThat(actual).isEqualTo(outStr)
    }


}

