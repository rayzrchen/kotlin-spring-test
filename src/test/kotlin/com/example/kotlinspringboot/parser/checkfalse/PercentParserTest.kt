package com.example.kotlinspringboot.parser.checkfalse

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class PercentParserTest {

    @ParameterizedTest
    @ValueSource(strings = [
        "a%false%,, [a]",
        "a bc%false%,, [bc]",
        "a {b}%false%,, [b]",
        "a|b%false%,, [b]",
        "a|b%false%,, [b]",
        "a {b|c}%false%,, [b|c]",
        "a {b|c d}%false%,, [b|c d]",
        "a {b|{c d}}%false%,, [b|{c d}]",
        "a %any%[1_2] {b|{c d}}%false% ,, [b|{c d}]",
        "a {b|c d}%false% {e f%false%},, [b|c d, f]",
        "a {b|c d}%false% {e f}%false%,, [b|c d, e f]"
    ])
    internal fun `parse tests`(s: String) {
        val split = s.split("""\s*,,\s*""".toRegex())
        assertThat(getPreviousGroupOrWord(split[0]).toString()).isEqualTo(split[1])
    }

    fun removeAllButFalseFunction(s: String): String {
        return s.replace("""%(?!false)[^ %]+%""".toRegex(), "")
    }

    fun getPreviousGroupOrWord(s: String): List<String> {
        val falseDelimitedStrings = falseDelimitedStrings(removeAllButFalseFunction(s))
        return falseDelimitedStrings.map {
            if (it[it.length - 1] != '}') {
                it.substringAfterLast(" ").substringAfterLast("|")
            } else {
                findLastGroup(it)
            }
        }
                .filter { it.isNotBlank() }
                .toList()

    }

    private fun findLastGroup(s: String): String {
        if (s.length == 1) return ""
        var level = 0

        val length = s.length
        for (i in length - 1 downTo 0) {
            when {
                s[i] == '}' -> level++
                s[i] == '{' -> {
                    level--
                    if (level == 0) {
                        return s.substring(i + 1, length - 1)
                    }
                }
            }
        }

        if (level != 0) {
            throw IllegalStateException("un-balance group")
        }
        return ""
    }

    fun falseDelimitedStrings(s: String): List<String> {
        return s.toLowerCase().split("%false%").filter { it.isNotBlank() }
    }

    fun findFalseStartPositions(s: String): List<IntRange> {
        val all = "%false%".toRegex(RegexOption.IGNORE_CASE).findAll(s)
        return all.map {
            it.range
        }.toList()
    }


}
