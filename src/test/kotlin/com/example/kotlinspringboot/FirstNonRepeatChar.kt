package com.example.kotlinspringboot

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class FirstNonRepeatChar {
    @Test
    internal fun case1() {
        val expect = 'g'
        val actual = firstNonRepeatChar("a green apple")
        assertThat(actual).isEqualTo(expect)

    }

    @Test
    internal fun case2() {
        val expect = 'e'
        val actual = firstRepeatedChar("green apple")
        assertThat(actual).isEqualTo(expect)
    }

    private fun firstRepeatedChar(s: String): Char {
        val map = mutableMapOf<Char, Int>()
        for (ch in s) {
            if (map.containsKey(ch)) {
                return ch
            } else {
                map[ch] = 1
            }
//            map[ch] = if (ch in map.keys) {
//                map[ch]!! + 1
//            } else {
//                1
//            }
        }
//
//        for (ch in s) {
//            if (map[ch]!! > 1) {
//                return ch
//            }
//        }

        return Char.MIN_VALUE
    }

    private fun firstNonRepeatChar(longString: String): Char {
        val map = mutableMapOf<Char, Int>()
        longString
                .forEach {
                    if (!map.containsKey(it)) {
                        map[it] = 1
                    } else {
                        map[it] = map[it]!! + 1
                    }
                }
        for (ch in longString) {
            if (map[ch] == 1) {
                return ch
            }
        }
        return Char.MIN_VALUE

    }
}
