package com.example.kotlinspringboot.my

class MyParser {

    val specialCharacters = "{}|[]%?*\\"

    fun parse(lexicon: String): String {

        val replace = lexicon.replace("%!--.+--%\\s*".toRegex(), "")

        val map = replace.split(" ")
                .map {
                    if (it.contains("|")) {
                        it.split("|")
                    } else {
                        listOf(it)
                    }
                }
                .also { println(it) }
                .toTypedArray()


        val list = cartesianProduct(*map)
                .map { s ->
                    s.joinToString(" ")
                }
                .also { println(it) }
                .map {
                    if (it.contains(" ")) "\"$it\"" else it
                }

        list.joinToString(" ")
                .also { println(it) }

        return list.joinToString(" ")
    }

    fun cartesianProduct(vararg lists: List<String>): List<List<String>> =
            when (lists.size) {
                0 -> emptyList()
                1 -> listOf(lists[0])
                else -> lists
                        .fold(listOf(listOf())) { acc, set ->
                            acc.flatMap { list -> set.map { element -> list + element } }
                        }
            }


}
