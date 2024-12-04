package at.kocmana.aoc24.dec03

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MultiplierTest {

    @Test
    fun parseReturnsCorrectResult() {
        val expectedResult = 161L
        val input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"

        val actualResult = parse(input)
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun parseConditionalReturnsCorrectResult() {
        val expectedResult = 48L
        val input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"

        val actualResult = parseWithEnabler(input)
        assertEquals(expectedResult, actualResult)
    }

}