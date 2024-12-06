package at.kocmana.aoc24.dec04

import at.kocmana.helper.Parser


fun main() {
    val grid = Parser().readFileTo2dArray("/dec04/xmas.txt")
    var result = 0;
    grid.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { columnIndex, _ -> result += getNumberOfHits(columnIndex to rowIndex, grid) }
    }
    println(result)
}

val directions = listOf(
    { coordinates: Pair<Int, Int> -> coordinates.first + 1 to coordinates.second + 0 },
    { coordinates: Pair<Int, Int> -> coordinates.first + 1 to coordinates.second + 1 },
    { coordinates: Pair<Int, Int> -> coordinates.first + 0 to coordinates.second + 1 },
    { coordinates: Pair<Int, Int> -> coordinates.first - 1 to coordinates.second + 1 },
    { coordinates: Pair<Int, Int> -> coordinates.first - 1 to coordinates.second + 0 },
    { coordinates: Pair<Int, Int> -> coordinates.first - 1 to coordinates.second - 1 },
    { coordinates: Pair<Int, Int> -> coordinates.first + 0 to coordinates.second - 1 },
    { coordinates: Pair<Int, Int> -> coordinates.first + 1 to coordinates.second - 1 },
)

fun getNumberOfHits(coordinate: Pair<Int, Int>, grid: Array<Array<String>>) =
    directions.asSequence()
        .map { direction ->
            getFieldsForDirection(coordinate, direction)
                .filter { it.first in grid.indices && it.second in grid.indices }
                .joinToString("") { grid[it.first][it.second] }
        }
        .filter { it == "XMAS" }
        .count()

private fun getFieldsForDirection(start: Pair<Int, Int>, direction: (Pair<Int, Int>) -> Pair<Int, Int>) =
    generateSequence(start) { direction.invoke(it) }
        .take(4)
        .toList()