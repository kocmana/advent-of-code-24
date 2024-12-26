package at.kocmana.aoc24.dec08

import at.kocmana.helper.Parser
import at.kocmana.helper.getPairCombinations

fun main() {

    val grid = Parser().readFileTo2dArray("/dec08/ResonantCollinearity.txt")
    println(calculateAntinodes(grid).size)
}

fun calculateAntinodes(grid: Array<Array<String>>) =
    extractPositions(grid).values.asSequence()
        .map { getPairCombinations(it) }
        .flatten()
        .map { it.first.calculateAntinodePositions(it.second) }
        .filter { it.isWithinGrid(grid) }
        .toSet()

fun extractPositions(input: Array<Array<String>>): Map<String, MutableList<Point>> {
    val positions = mutableMapOf<String, MutableList<Point>>()
    input.forEachIndexed { i, row ->
        row.forEachIndexed { j, _ ->
            if (input[i][j] != ".") {
                val key = input[i][j]
                positions.computeIfAbsent(key) { mutableListOf() }
                    .add(Point(i, j))
            }
        }
    }
    return positions.toMap()
}

data class Point(val x: Int, val y: Int) {

    infix fun vectorTo(other: Point) =
        other.x - this.x to other.y - this.y

    fun addVector(vector: Pair<Int, Int>) =
        Point(x + vector.first, y + vector.second)

    fun calculateAntinodePositions(other: Point): Point =
        addVector(other vectorTo this)

    fun isWithinGrid(grid: Array<Array<String>>): Boolean =
        this.x in grid.indices && this.y in grid[0].indices
}