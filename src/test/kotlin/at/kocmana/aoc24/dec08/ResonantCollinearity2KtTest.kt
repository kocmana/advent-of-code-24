package at.kocmana.aoc24.dec08

import at.kocmana.helper.Parser
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ResonantCollinearity2KtTest {
    @Test
    fun calculateAntinodePositionsV2() {
        val antenna1 = Point(0, 0)
        val antenna2 = Point(1, 2)
        val grid = Parser().readFileTo2dArray("/dec08/test-input-part2.txt")

        val actualResult = antenna1.calculateAntinodesV2(antenna2, grid)

        assertEquals(5, actualResult.size)
    }

    @Test
    fun calculateAntinodePositions() {
        val grid = Parser().readFileTo2dArray("/dec08/test-input-part2.txt")

        val actualResult = calculateAntinodesV2(grid)
        assertEquals(9, actualResult.size)
    }
}