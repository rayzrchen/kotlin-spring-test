package com.example.kotlinspringboot.calculator

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class CalculatorTest {

    @ParameterizedTest
    @CsvSource(
            "1+2 , 3",
            "1+2+3, 6",
            "2-1 , 1",
            "2*3 + 2*5 , 16",
            "(2*3 + 2)*5 , 40",
            delimiter = ','
    )
    internal fun `all tests`(ip: String , op: String  ) {
        Calculator.parse(ip + "\n")
    }

//    @Test
//    internal fun `lineBreak test`() {
//        assertThat(Calculator.parse("1+2\n3+4\n")).isEqualTo(10.0)
//    }
}
