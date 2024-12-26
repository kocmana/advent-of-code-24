package at.kocmana.aoc24.dec08

import at.kocmana.helper.Parser
import at.kocmana.helper.getPairCombinations

fun main() {

    val grid = Parser().readFileTo2dArray("/dec08/ResonantCollinearity.txt")
    println(calculateAntinodesV2(grid).size)
}


fun calculateAntinodesV2(grid: Array<Array<String>>) =
    extractPositions(grid).values.asSequence()
        .map { getPairCombinations(it) }
        .flatten()
        .map { it.first.calculateAntinodesV2(it.second, grid) }
        .flatten()
        .toSet()

fun Point.calculateAntinodesV2(other: Point, grid: Array<Array<String>>) =
    generateSequence(this) { it.addVector(this vectorTo other) }
        .takeWhile { it.isWithinGrid(grid) }
        .toSet()