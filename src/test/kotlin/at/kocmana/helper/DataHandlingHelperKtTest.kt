package at.kocmana.helper

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DataHandlingHelperKtTest {
    @Test
    fun getPairCombinations() {
        val input = listOf("A", "B", "C")

        val actualResult = getPairCombinations(input)

        assertEquals(8, actualResult.size)

    }

}