package at.kocmana.aoc24.dec10

import at.kocmana.helper.Board

fun main() {
    val board = Board("/dec10/HoofIt.txt") { it.toInt() }
    val startingPoints = board.findStartingPoints(board)
    val result = board.calculatePathsPart2(startingPoints)
    println(result)
}

fun Board<Int>.calculatePathsPart2(startingPoints: List<Board<Int>.Position>) =
    startingPoints.asSequence()
        .map { this.iterateToNextLevelPart2(1, it) }
        .sum()

fun Board<Int>.iterateToNextLevelPart2(value: Int, position: Board<Int>.Position): Int {
    if (position.getValue() >= maxValue) return 1

    val nextPositions = position.getApplicableDirections { it == value }.asSequence()
        .map { position andOneStepTo it }
        .toSet()

    if (nextPositions.isEmpty()) return 0

    return nextPositions.asSequence()
        .map { this.iterateToNextLevelPart2(value + 1, it) }
        .sum()
}