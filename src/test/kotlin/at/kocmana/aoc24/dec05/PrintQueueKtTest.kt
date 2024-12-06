package at.kocmana.aoc24.dec05

import at.kocmana.helper.Parser
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PrintQueueKtTest {
    @Test
    fun parsePageInstruction() {
        val input = "47|53"

        val actual = parsePageInstructions(input)

        assertEquals(47 to 53, actual)
    }

    @Test
    fun printPages() {
        val input = "75,47,61,53,29"

        val actual = parsePages(input)

        assertEquals(listOf(75,47,61,53,29), actual)
    }

    @Test
    fun parsePageInstructions() {
        val input = Parser().readFile("/dec05/test-input.txt")

        val actual = parsePageInstructions(input)

        assertEquals(21, actual.size)
    }

    @Test
    fun parsePages() {
        val input = Parser().readFile("/dec05/test-input.txt")

        val actual = parsePages(input)

        assertEquals(6, actual.size)
    }

}