package at.kocmana.aoc24.dec12

import at.kocmana.helper.Board
import at.kocmana.helper.getNeighboringPositionsAndNullWhere

fun main() {
    val board = Board("/dec12/GardenGroups.txt") { it[0] }
    println(solveBoard(board))
}

fun solveBoard(board: Board<Char>): Long {
    var nextPosition: Board<Char>.Position? = board.Position(0, 0)
    val coveredPositions = mutableSetOf<Board<Char>.Position>()
    var total = 0L;
    do {
        val garden = exploreGarden(mutableSetOf<Board<Char>.Position>(), nextPosition!!)
        val border = getAllBordersFor(garden)
        total += garden.size * border.size

        coveredPositions.addAll(garden)
        nextPosition = board.getPositionsAsSequence()
            .find { !coveredPositions.contains(it) }
    } while (nextPosition != null)
    return total
}

fun getAllBordersFor(garden: Set<Board<Char>.Position>) =
    garden.asSequence()
        .map { generateBordersForPosition(it) }
        .flatten()
        .toSet()

fun generateBordersForPosition(position: Board<Char>.Position) =
    position.getNeighboringPositionsAndNullWhere { it != position.getValue() }
        .map { Border(position, it) }

fun exploreGarden(allFields: MutableSet<Board<Char>.Position>, currentPosition: Board<Char>.Position):
        Set<Board<Char>.Position> {
    val newFields = currentPosition.getNeighboringPositionsWhere { it == currentPosition.getValue() }.asSequence()
        .filter { !allFields.contains(it) }
        .toSet()

    if (newFields.isEmpty()) return allFields + currentPosition
    allFields.addAll(newFields)

    return newFields.asSequence()
        .map { exploreGarden(allFields, it) }
        .flatten()
        .toSet()
}

data class Border(val firstPosition: Board<Char>.Position, val secondPosition: Board<Char>.Position) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Border

        if (firstPosition == other.firstPosition && secondPosition != other.secondPosition) return true
        if (secondPosition == other.firstPosition && firstPosition != other.secondPosition) return true
        return false
    }

    override fun hashCode(): Int {
        var result = firstPosition.hashCode()
        result = 31 * result + secondPosition.hashCode()
        return result
    }

}