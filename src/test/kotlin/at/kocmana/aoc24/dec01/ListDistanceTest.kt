package at.kocmana.aoc24.dec01

import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ListDistanceTest {

    val firstList = listOf(3, 4, 2, 1, 3, 3)
    val secondList = listOf(4, 3, 5, 3, 9, 3)

    @Test
    fun getDistancesWorks() {
        val expected = listOf(2, 1, 0, 1, 2, 5)

        val actual = getDistances(firstList, secondList)
        assertIterableEquals(expected, actual)
    }

    @Test
    fun getTotalDistanceWorks(){
        val actual = calculateTotalDistance(firstList, secondList)
        assertEquals(11, actual)
    }

}