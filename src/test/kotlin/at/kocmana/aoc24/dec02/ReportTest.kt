package at.kocmana.aoc24.dec02

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertContains

class ReportTest {

    @TestFactory
    fun assessIfSafe() = listOf(
        listOf(7, 6, 4, 2, 1) to true,
        listOf(1, 2, 7, 8, 9) to false,
        listOf(9, 7, 6, 2, 1) to false,
        listOf(1, 3, 2, 4, 5) to false,
        listOf(8, 6, 4, 4, 1) to false,
        listOf(1, 3, 6, 7, 9) to true,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("assessIfSafe($input)") {
            assertEquals(expected, Report(input).assessIfSafe())
        }
    }

    @Test
    fun generateMutations() {
        var numbers = listOf(1,2,3,4)

        var actualReports = Report(numbers).generateMutations()

        println(actualReports)
        assertEquals(actualReports.size, 4)
    }
}