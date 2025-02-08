package at.kocmana.aoc24.dec10

import at.kocmana.helper.Board

const val maxValue = 9

fun main() {
    val board = Board("/dec10/HoofIt.txt") { it.toInt() }
    val startingPoints = findStartingPoints(board)
    val result = calculatePaths(board, startingPoints)
    println(result)
}

fun findStartingPoints(board: Board<Int>) =
    board.findAllPositionsWhere { it == 0 }

fun calculatePaths(board: Board<Int>, startingPoints: List<Board<Int>.Position>) =
    startingPoints.asSequence()
        .map { iterateToNextLevel(board, 1, it) }
        .map { it.size }
        .sum()

fun iterateToNextLevel(board: Board<Int>, value: Int, position: Board<Int>.Position): Set<Board<Int>.Position> {
    val currentValue = position.getValue()
    if (Integer.valueOf(position.getValue()) >= maxValue) {
        return setOf(position)
    }

    val nextPositions = position.getApplicableDirections { it == value }.asSequence()
        .map { position.andOneStepTo(it) }
        .toSet()

    if (nextPositions.isEmpty()) return emptySet()

    return nextPositions.asSequence()
        .flatMap { iterateToNextLevel(board, value + 1, it) }
        .toSet()
}