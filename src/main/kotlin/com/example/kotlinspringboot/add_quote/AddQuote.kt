package com.example.kotlinspringboot.add_quote

interface AddQuoteService {
    fun addQuoteForString(s: String): String
}


class AddQuote : AddQuoteService {
    override fun addQuoteForString(s: String): String {
        return s.replace("""(?<!")([^" ()+]+)(?!")""".toRegex(), "\"$1\"")
    }

}

