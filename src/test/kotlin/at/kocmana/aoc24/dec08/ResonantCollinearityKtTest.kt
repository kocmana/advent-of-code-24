package at.kocmana.aoc24.dec08

import at.kocmana.helper.Parser
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

class ResonantCollinearityKtTest {
    @Test
    fun extractPositions() {
        val input = Parser().readFileTo2dArrayAndTranspose("/dec08/test-input.txt")

        val actualResult = extractPositions(input)

        assertEquals(expected = 2, actualResult.size)
    }

    @Test
    fun calculateVector() {
        val antenna1 = Point(5, 4)
        val antenna2 = Point(6, 6)

        val actualResult = antenna1 vectorTo antenna2

        assertEquals(1 to 2, actualResult)
    }

    @Test
    fun addVector() {
        val antenna1 = Point(5, 4)
        val vector = 1 to 2

        val actualResult = antenna1.addVector(vector)

        assertEquals(Point(6, 6), actualResult)
    }


    @Test
    fun calculateAntinodePositions() {
        var antenna1 = Point(5, 4)
        var antenna2 = Point(6, 6)

        val actualResult = antenna1.calculateAntinodePositions(antenna2)

        assertEquals(Point(4, 2), actualResult)
    }

    @ParameterizedTest
    @CsvSource(
        "/dec08/test-antenna.txt, 4",
        "/dec08/test-input.txt, 14",
    )
    fun calculateNumber(filename:String, expected:Int) {
        val input = Parser().readFileTo2dArrayAndTranspose(filename)

        val actualResult = calculateAntinodes(input).also { println(it) }

        assertEquals(expected, actualResult.size)
    }

}