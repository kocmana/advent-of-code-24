package at.kocmana.helper

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ParserTest {
    @Test
    fun readFile() {
        val expected = listOf("first", "second")
        val actual = Parser().readFile("/helper/test-resource.txt")

        assertLinesMatch(actual, expected)
    }

    @Test
    fun readAndModifyFile() {
        val expected = listOf(5, 6)
        val actual = Parser().readAndModify("/helper/test-resource.txt") { it.length }

        assertIterableEquals(actual, expected)
    }

}