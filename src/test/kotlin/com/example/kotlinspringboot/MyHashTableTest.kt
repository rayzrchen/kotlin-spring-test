package com.example.kotlinspringboot

import org.junit.jupiter.api.Test
import java.util.*

class MyHashTableTest {
    @Test
    internal fun put() {
        val table = MyHashTable()
        table.put(6, "A")
        table.put(8, "B")
        table.put(11, "C")
        println(table.get(6))
        println(table.get(8))
        println(table.get(11))
        table.put(6, "A+")
        println(table.get(6))
    }
}

class MyHashTable {

    data class Entry(var key: Int, var value: String)

    private var table = Array(5) { LinkedList<Entry>() }


    fun put(k: Int, v: String) {
        val index = makeIndex(k)
        val linkedList = table[index]

        val containsKeyAlready = linkedList.any {
            it.key == k
        }

        if (containsKeyAlready) {
            linkedList.first { it.key == k }.value = v
        } else {
            linkedList.addLast(Entry(k, v))
        }

    }


    fun makeIndex(k: Int): Int {
        return k % table.size
    }

    fun get(k: Int): String {
        val linkedList = table[makeIndex(k)]
        return if (linkedList.isEmpty()) {
            ""
        } else {
            linkedList
                    .firstOrNull {
                        it.key == k
                    }
                    ?.value
                    ?: ""
        }
    }

}

