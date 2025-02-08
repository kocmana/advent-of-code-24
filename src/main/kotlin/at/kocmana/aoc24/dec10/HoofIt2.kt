package at.kocmana.aoc24.dec10

import at.kocmana.helper.Board

fun main() {
    val board = Board("/dec10/HoofIt.txt") { it.toInt() }
    val startingPoints = findStartingPoints(board)
    val result = calculatePathsPart2(board, startingPoints)
    println(result)
}

fun calculatePathsPart2(board: Board<Int>, startingPoints: List<Board<Int>.Position>) =
    startingPoints.asSequence()
        .map { iterateToNextLevelPart2(board, 1, it) }
        .sum()

fun iterateToNextLevelPart2(board: Board<Int>, value: Int, position: Board<Int>.Position): Int {
    if (position.getValue() >= maxValue) {
        return 1
    }

    val nextPositions = position.getApplicableDirections { it == value }.asSequence()
        .map { position andOneStepTo it }
        .toSet()

    if (nextPositions.isEmpty()) return 0

    return nextPositions.asSequence()
        .map { iterateToNextLevelPart2(board, value + 1, it) }
        .sum()
}