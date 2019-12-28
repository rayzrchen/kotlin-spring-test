package com.example.kotlinspringboot.parser

fun preHandling(inString: String): String {
    return inString.replace("""%!--.+?--%""".toRegex(), "")
            .replace("""%(?!(any|false|PrecededBy))[^ %]+%""".toRegex(RegexOption.IGNORE_CASE), "")
            .replace("""\{}""".toRegex(), "")
            .replace("""\s+""".toRegex(), " ")
            .trim()
}
