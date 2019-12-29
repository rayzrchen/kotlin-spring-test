package com.example.kotlinspringboot.add_quote

interface AddQuoteService {
    fun addQuoteForNonAsciiString(s: String): String
}


class AddQuote : AddQuoteService {

    override fun addQuoteForNonAsciiString(s: String): String {
        return s.replace("([^\\p{ASCII}]+)".toRegex(), "\"$1\"")
    }

}

