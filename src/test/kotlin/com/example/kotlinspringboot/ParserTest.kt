package com.example.kotlinspringboot

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.StreamTokenizer
import java.io.StringReader

internal class ParserTest {
//    @Test
//    internal fun main() {
//        try {
////            Parser.PROGRAM.parse("begin \n" +
//                    "total = var1 + var2; \n" +
//                    "while (var1 < var2) \n" +
//                    "while ( var3 > var4)\n" +
//                    "var2 = var2 - var1 \n" +
//                    "end")
//            println("OK")
//        } catch (e: ParseException) {
//            println(e.message)
//        }
//    }

    @Test
    internal fun token() {
        val tokenizer = StreamTokenizer(StringReader("abc ddd"))
        println(tokenizer.nextToken())





    }
}
