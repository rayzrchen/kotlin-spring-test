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

    private fun addQuote(it: String) = if (it.contains("[ +-]".toRegex())) "'$it'" else it

    private fun appendOperator(s: String, operator: String): String {
        return if (s.contains(" ")) {
            "$operator($s)"
        } else {
            "$operator$s"
        }

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

    private fun handleNegative(negativeList: MutableList<List<String>>): String {
        val filteredList = negativeList.flatten().filter { it.isNotEmpty() }
        return when {
            filteredList.isEmpty() -> ""
            filteredList.size == 1 -> " " + appendOperator(filteredList[0], "-")
            else -> " -(" + filteredList.joinToString(" ") { addQuote(it) } + ")"
        }
    }

    private fun handlePositive(positiveList: List<String>): String {
        val filteredList = positiveList.filter { it.isNotEmpty() }
                .filter { !it.contains("%") }

        return when {
            filteredList.isEmpty() -> ""
            filteredList.size == 1 -> filteredList[0]
            else -> filteredList.joinToString(" ") {
                appendOperator(it, "+")
            }
        }
    }

    fun handleParts(positiveList: List<String>, negativeList: MutableList<List<String>>): String {
        return "${handlePositive(positiveList)}${handleNegative(negativeList)}"
    }

    fun getPartString(list: MutableList<String>): String {
        return list.joinToString(" ") { addQuote(it) }
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

