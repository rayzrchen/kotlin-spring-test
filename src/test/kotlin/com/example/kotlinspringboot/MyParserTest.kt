package com.example.kotlinspringboot
//
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.jupiter.params.ParameterizedTest
//import org.junit.jupiter.params.provider.CsvSource
//
//
//internal class MyParserTest {
//
//
//    @ParameterizedTest
//    @CsvSource(
//            "a b,+a +b",
//            "a阿联酋迪拉 b,+a阿联酋迪拉 +b",
//            "a阿联酋迪拉* b,+a阿联酋迪拉* +b",
//            "a阿联酋?迪拉 b,+a阿联酋?迪拉 +b",
//            "a阿联酋迪拉! b,+a阿联酋迪拉! +b",
//            "a阿联酋迪拉. b,+a阿联酋迪拉. +b",
//            "a-阿联酋迪拉. b,+a-阿联酋迪拉. +b",
//            "a9阿联酋迪拉. b,+a9阿联酋迪拉. +b",
//            "a9 (阿联酋迪拉.|c) b,+a9 +(阿联酋迪拉. c) +b",
//            "a (b c),+a +(+b +c)",
//            "\$a (\$100b! c),+\$a +(+\$100b! +c)",
//            "\$a (\$100b. c),+\$a +(+\$100b. +c)",
//            "\$a (\$100b- c),+\$a +(+\$100b- +c)",
//            "\$a (\$100b? c),+\$a +(+\$100b? +c)",
//            "\$a (\$100b* c),+\$a +(+\$100b* +c)",
//            "\$a (\$100b& c),+\$a +(+\$100b& +c)",
//            "a|b,(a b)",
//            "a|b|c,(a b c)",
//            "aa|bb|(cc|(ff gg)) hh,+(aa bb (cc (+ff +gg))) +hh",
//            "(aa(bb(cc|ee)|ff) gg),(+aa(bb(cc ee) ff) +gg)",
//            "(a b),(+a +b)",
//            "(a(c|d) b),(+a(c d) +b)",
//            "bb(cc|ee),bb(cc ee)",
//            "((a|b) (a b)|b (c|d)|e),(+(a b) +((+a +b) b) +((c d) e))"
//    )
//    fun testTransformString(input: String, output: String) {
////        val myParser = MyParser()
//        assertThat(myParser.transformString(input)).isEqualTo(output)
//    }
//
//    @ParameterizedTest
//    @CsvSource(
//            "a b,+a +b",
//            "a b c,+a +b +c",
//            "a|b,(a b)",
//            "(a b),(+a +b)",
//            "(a|b),(a b)",
//            "a|b|c,(a b c)",
//            "(aa|bb cc|dd),+(aa bb) +(cc dd)",
//            "(aa|bb|ee cc|dd),(+(aa bb ee) +(cc dd))",
//            "aa|bb|cc|ff gg hh,(+(aa bb cc ff) +gg +hh)"
//    )
//    fun testTransformBasic(input: String, output: String) {
////        val myParser = MyParser()
//        assertThat(myParser.transformBasic(input)).isEqualTo(output)
//    }
//}
//
