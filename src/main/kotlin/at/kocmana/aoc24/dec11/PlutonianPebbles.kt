package at.kocmana.aoc24.dec11

import at.kocmana.helper.Parser

fun main() {
    println(solve("/dec11/plutonian-pebbles.txt"))
}

fun solve(file: String): Int {
    val input = Parser().readToIntLists(file, " ").first().map { it.toLong() }

    return generateSequence(input) { calculateNextBlink(it) }
        .take(26)
        .last()
        .count()
}

fun calculateNextBlink(input: List<Long>) =
    input.asSequence()
        .map(::calculateNextStone)
        .flatten()
        .toList()

fun calculateNextStone(currentStone: Long) =
    when {
        currentStone == 0L -> listOf(1L)
        currentStone.toString().length % 2 == 0 -> splitStone(currentStone)
        else -> listOf(currentStone * 2024)
    }

fun splitStone(currentStone: Long): List<Long> {
    val middleIndex = currentStone.toString().length / 2
    return listOf(
        currentStone.toString().substring(0, middleIndex).toLong(),
        currentStone.toString().substring(middleIndex).toLong()
    )
}