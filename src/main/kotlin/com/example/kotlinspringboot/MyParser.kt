package com.example.kotlinspringboot

import java.util.*
import java.util.regex.Pattern


class MyParser {

    val validChar = "\\p{LD}$*?^&%#@!_=\\<>/;',.-"

    fun transformString(input: String): String {
        val childParams = Stack<Pair<Int, String>>()
        var parsedInput = input
        var nextInt = Integer.MAX_VALUE
        val pattern = Pattern.compile("\\(([$validChar]|\\|| )+\\)")
        var matcher = pattern.matcher(parsedInput)
        while (matcher.find()) {
            nextInt--
            parsedInput = matcher.replaceFirst(nextInt.toString())
            val childParam = matcher.group()
            childParams.add(Pair(nextInt, childParam))
            matcher = pattern.matcher(parsedInput)
        }

        parsedInput = transformBasic(parsedInput)
        while (!childParams.empty()) {
            val childGroup = childParams.pop()
            parsedInput = parsedInput.replace(childGroup.first.toString(), transformBasic(childGroup.second))
        }
        return parsedInput
    }

    // Transform basic only handles strings that contain words. This allows us to simplify the problem
    // and not have to worry about child groups or nested groups.
    fun transformBasic(input: String): String {
        var transformedBasic = input
        if (input.startsWith("(")) {
            transformedBasic = input.substring(1, input.length - 1)
        }

        // Append + in front of each word if there are multiple words.
        if (transformedBasic.contains(" ")) {
            transformedBasic = transformedBasic.replace("( )|^".toRegex(), "$1+")
        }
        // Surround all words containing | with parenthesis.
        transformedBasic = transformedBasic.replace("([$validChar]+\\|[$validChar|]*[$validChar]+)".toRegex(), "($1)")
        // Replace pipes with spaces.
        transformedBasic = transformedBasic.replace("|", " ")
        if (input.startsWith("(") && !transformedBasic.startsWith("(")) {
            transformedBasic = "($transformedBasic)"
        }
        return transformedBasic
    }
}
