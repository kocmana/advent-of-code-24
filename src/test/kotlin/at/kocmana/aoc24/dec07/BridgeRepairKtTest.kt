package at.kocmana.aoc24.dec07

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BridgeRepairKtTest {
    @Test
    fun toCalculation() {
        val input = "3267: 81 40 27"

        val actual = input.toCalculation()

        assertEquals(3267, actual.result)
        assertEquals(3, actual.numbers.size)
        assertIterableEquals(listOf(81, 40, 27), actual.numbers)
    }

    @Test
    fun calculate() {
        val input = "3267: 81 40 27"

        val actual = input.toCalculation()

        assertTrue { calculate(actual.result, 0, actual.numbers, MathOperand.ADD).contains(3267) }
    }


    @Test
    fun assertCalculation() {
        val calculation = Calculation(3267, listOf(81,40,27))

        val actual = assertCalculation(calculation)

        assertTrue (actual)
    }

}