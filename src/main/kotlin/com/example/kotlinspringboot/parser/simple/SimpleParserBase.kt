package com.example.kotlinspringboot.parser.simple



open class SimpleParserBase {

    fun singleElementList(inString: String, repeat: Token?): MutableList<String> {
        return mutableListOf(
                if (repeat == null) {
                    inString
                } else {
                    ""
                }
        )
    }

    private fun addQuote(it: String) = if (it.contains("""[ +-]""".toRegex())) "'$it'" else it

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


    fun getPartString(list: MutableList<String>): String {
        return list.joinToString(" ") { addQuote(it) }
    }

    fun groupConsiderRepeat(
            list: MutableList<String>,
            repeat: Token?
    ): MutableList<String> {
        return if (repeat == null) {
            list
        } else {
            mutableListOf("")
        }
    }

}

