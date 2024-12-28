package at.kocmana.helper

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DataHandlingHelperKtTest {
    @Test
    fun getPairCombinations() {
        val input = listOf("A", "B", "C")

        val actualResult = getPairCombinations(input)

        assertEquals(8, actualResult.size)

    }

    @Test
    fun cutAndInsertPartialList() {
        val input = mutableListOf("A", "B", "C", "D", "E")
        val expectedResult = listOf("A", "C", "D", "B", "E")

        input.cutAndInsertPartialList(fromIndex = 2, length = 2, targetIndex = 1)

        assertEquals(expectedResult, input)
    }

}