package at.kocmana.aoc24.dec04

import at.kocmana.helper.Parser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class XmasKtTest {

    @Test
    fun testGetNumberOfHits() {
        val array = Parser().readFileTo2dArray("/helper/test-matrix.txt")

        val actual = getNumberOfHits(4 to 4, array)

        assertEquals(4, actual)

    }
}