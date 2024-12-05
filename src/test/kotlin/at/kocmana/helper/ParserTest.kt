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

    @Test
    fun readAndParseTo2dArray() {
        val actual = Parser().readFileTo2dArray("/helper/test-matrix.txt")

        assertEquals("0", actual[0][0]);
        assertEquals("1", actual[0][1]);
        assertEquals("a", actual[1][0]);
    }

}