package at.kocmana.aoc24.dec10

import at.kocmana.helper.Board

const val maxValue = 9

fun main() {
    val board = Board("/dec10/HoofIt.txt")
    val result = calculatePaths(board)
    println(result)
}

fun calculatePaths(board: Board): Int {
    val startingPoints = findStartingPoints(board).also { println("Starting points: $it") }
    val trailheads = startingPoints.map { iterateToNextLevel(board, 1, it) }.also { println(it) }
        .map { it.size }
    return trailheads.sum()
}

fun findStartingPoints(board: Board) =
    board.findAllPositionsWhere { it == 0 }

fun iterateToNextLevel(board: Board, value: Int, position: Board.Position): Set<Board.Position> {
    val currentValue = position.getValue()
    if (Integer.valueOf(position.getValue()) >= maxValue) {
        return setOf(position)
    }

    val nextPositions = position.getApplicableDirections { it == value }.asSequence()
        .map { position.andOneStepTo(it) }
        .toSet()

    if(nextPositions.isEmpty()) return emptySet()

    return nextPositions.asSequence()
        .flatMap { iterateToNextLevel(board, value + 1, it) }
        .toSet()
}