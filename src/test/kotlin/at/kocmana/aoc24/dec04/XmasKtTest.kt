package at.kocmana.aoc24.dec04

import at.kocmana.helper.Parser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class XmasKtTest {

    @Test
    fun testGetNumberOfHits() {
        val array = Parser().readFileTo2dArray("/dec04/xmas-matrix.txt")

        val actual = getNumberOfHits(0 to 4, array)

        assertEquals(1, actual)

    }
}