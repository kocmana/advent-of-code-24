package at.kocmana.aoc24.dec10

import at.kocmana.helper.Board

fun main() {
    val board = Board("/dec10/HoofIt.txt") { it.toInt() }
    val startingPoints = board.findStartingPoints(board)
    val result = calculatePathsPart2(startingPoints)
    println(result)
}

fun calculatePathsPart2(startingPoints: List<Board<Int>.Position>) =
    startingPoints.sumOf { iterateToNextLevelPart2(1, it) }

fun iterateToNextLevelPart2(value: Int, position: Board<Int>.Position): Int {
    if (position.getValue() >= maxValue) return 1

    val nextPositions = position.getApplicableDirections { it == value }.asSequence()
        .map { position andOneStepTo it }
        .toSet()

    if (nextPositions.isEmpty()) return 0

    return nextPositions.asSequence()
        .map { iterateToNextLevelPart2(value + 1, it) }
        .sum()
}