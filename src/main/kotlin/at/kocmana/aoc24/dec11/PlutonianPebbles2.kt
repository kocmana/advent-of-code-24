package at.kocmana.aoc24.dec11

import at.kocmana.helper.Parser

fun main() {
    println(solve("/dec11/plutonian-pebbles.txt"))
}

private fun solve(file: String): Long {
    val input = Parser().readToIntLists(file, " ")
        .first()
        .map { it.toLong() }
        .associateWith { _ -> 1L }.also { println(it) }

    return generateSequence(input) { calculateNextBlink(it) }
        .take(76)
        .last().also { println("Size: ${it.size} - Elements: $it") }
        .map { it.value }
        .sum()
}

private fun calculateNextBlink(input: Map<Long, Long>) =
    input.asSequence()
        .flatMap { (key, value) -> calculateNextStone(key).map { it to value } }
        .groupingBy { it.first }
        .aggregate { _, accumulator: Long?, element, isFirst ->
            if (isFirst) element.second else accumulator!! + element.second
        }

private fun calculateNextStone(currentStone: Long) =
    when {
        currentStone == 0L -> listOf(1L)
        currentStone.toString().length % 2 == 0 -> splitStone(currentStone)
        else -> listOf(currentStone * 2024)
    }

private fun splitStone(currentStone: Long): List<Long> {
    val middleIndex = currentStone.toString().length / 2
    return listOf(
        currentStone.toString().substring(0, middleIndex).toLong(),
        currentStone.toString().substring(middleIndex).toLong()
    )
}
