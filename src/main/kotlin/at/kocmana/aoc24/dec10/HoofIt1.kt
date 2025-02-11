package at.kocmana.aoc24.dec10

import at.kocmana.helper.Board

const val maxValue = 9

fun main() {
    val board = Board("/dec10/HoofIt.txt") { it.toInt() }
    val startingPoints = board.findStartingPoints(board)
    val result = board.calculatePaths(startingPoints)
    println(result)
}

fun Board<Int>.findStartingPoints(board: Board<Int>) =
    this.findAllPositionsWhere { it == 0 }

fun Board<Int>.calculatePaths(startingPoints: List<Board<Int>.Position>) =
    startingPoints.asSequence()
        .map { this.iterateToNextLevel(1, it) }
        .map { it.size }
        .sum()

fun Board<Int>.iterateToNextLevel(value: Int, position: Board<Int>.Position): Set<Board<Int>.Position> {
    if (position.getValue() >= maxValue) return setOf(position)

    val nextPositions = position.getApplicableDirections { it == value }.asSequence()
        .map { position andOneStepTo it }
        .toSet()

    if (nextPositions.isEmpty()) return emptySet()

    return nextPositions.asSequence()
        .flatMap { this.iterateToNextLevel(value + 1, it) }
        .toSet()
}