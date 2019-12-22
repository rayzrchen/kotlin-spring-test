package com.example.kotlinspringboot

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.random.Random


class NonBlockingTest {

    @Test
    internal fun test() = runBlocking {
        (0..100000)
                .map {
                    async { something3(it) }
                }
                .forEach {
                    println(it.await())
                }
    }

    private suspend fun something3(it: Int): Int {
        delay(10000)
        return it
    }

}
