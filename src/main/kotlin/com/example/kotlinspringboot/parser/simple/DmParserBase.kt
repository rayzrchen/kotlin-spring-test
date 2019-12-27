package com.example.kotlinspringboot.parser.simple


fun preHandling(inString: String): String {
    return inString.replace("%!--.+?--%".toRegex(), "")
            .replace("%(?!(any|false|PrecededBy))[^ ]+%".toRegex(RegexOption.IGNORE_CASE), "")
            .replace("\\{}".toRegex(), "")
            .replace("\\s+".toRegex(), " ")
            .trim()
}


open class DmParserBase {

    fun singleElementList(inString: String, repeat: Token?, falseFunction: Token?, negativeList: MutableList<List<String>>): MutableList<String> {
        if (falseFunction != null) {
            negativeList.add(mutableListOf(inString))
        }

        return mutableListOf(
                if (falseFunction == null && repeat == null) {
                    inString
                } else {
                    ""
                }
        )

    }

    fun mergeToLeft(left: MutableList<String>, right: MutableList<String>): MutableList<String> {
        val filter1 = left.filter { it.isNotEmpty() }.toMutableList()
        val filter2 = right.filter { it.isNotEmpty() }.toMutableList()
        filter1.addAll(filter2)

        return filter1
    }

    fun getCombinationsOfTwoList(first: MutableList<String>, second: MutableList<String>, concatToken: Token?): MutableList<String> {
        val concatSpace = if (concatToken?.image ?: "" == "") "" else " "

        val firstNotEmpty = first.filter { it.isNotEmpty() }
        val secondNotEmpty = second.filter { it.isNotEmpty() }

        if (firstNotEmpty.isEmpty()) return secondNotEmpty.toMutableList()
        if (secondNotEmpty.isEmpty()) return firstNotEmpty.toMutableList()

        return firstNotEmpty
                .flatMap { firstListElement ->
                    secondNotEmpty
                            .map { secondListElement ->
                                "$firstListElement$concatSpace$secondListElement"
                            }
                }
                .toMutableList()

    }

    fun handleParts(positiveList: List<String>, negativeList: MutableList<List<String>>): String {

        fun appendOperator(s: String, operator: String): String {
            return if (s.contains(" ")) {
                "$operator($s)"
            } else {
                "$operator$s"
            }

        }

        fun handleMultipleParts(): String {
            return positiveList.filter { it.isNotEmpty() }
                    .filter { !it.contains("%") }
                    .joinToString(" ") {
                        appendOperator(it, "+")
                    }
        }


        fun handlePositive(): String {
            return when {
                positiveList.isEmpty() -> ""
                positiveList.size == 1 -> positiveList[0]
                else -> handleMultipleParts()
            }
        }

        fun handleMultiplePartsNegative(list: List<String>): String {
            return list.filter { it.isNotEmpty() }
                    .map { if (it.contains("[ +-]".toRegex())) "'$it'" else it }
                    .joinToString(" ")
        }


        fun handleNegative(): String {
            val flatNegativeList = negativeList.flatten()
            return when {
                flatNegativeList.isEmpty() -> ""
                flatNegativeList.size == 1 -> " " + appendOperator(flatNegativeList[0], "-")
                else -> " -(" + handleMultiplePartsNegative(flatNegativeList) + ")"
            }
        }

        val positive = handlePositive()
        val negative = handleNegative()

        return "$positive$negative"
    }


    fun getPartString(list: MutableList<String>): String {
        return list.joinToString(" ") { if (it.contains("[ +-]".toRegex())) "'$it'" else it }
    }

    fun groupConsiderRepeat(
            list: MutableList<String>,
            repeat: Token?,
            falseFunction: Token?,
            negativeList: MutableList<List<String>>
    ): MutableList<String> {

        if (falseFunction != null) {
            negativeList.add(list)
        }

        return if (repeat == null && falseFunction == null) {
            list
        } else {
            mutableListOf("")
        }
    }

}

