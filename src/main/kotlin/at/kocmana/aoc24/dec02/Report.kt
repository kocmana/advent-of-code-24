package at.kocmana.aoc24.dec02

import kotlin.math.abs

class Report(private val readings: List<Int>) {

    fun assessIfSafe(): Boolean {
        if (readings.size < 2) {
            return false
        }
        val initialDirection = determineDirection(readings[0] to readings[1])

        return readings.zipWithNext()
            .none {
                abs(it.first - it.second) > 3
                        || determineDirection(it) == Direction.STABLE
                        || determineDirection(it) != initialDirection
            }
    }

    fun generateMutations() =
        readings.asSequence()
            .mapIndexed { i, _ -> readings.filterIndexed { index, _ -> index != i }}
            .map( ::Report )
            .toList()

    private fun determineDirection(numbers: Pair<Int, Int>) =
        when {
            numbers.first < numbers.second -> Direction.INCREASING
            numbers.first > numbers.second -> Direction.DECREASING
            else -> Direction.STABLE
        }

    enum class Direction {
        STABLE,
        INCREASING,
        DECREASING;
    }

}