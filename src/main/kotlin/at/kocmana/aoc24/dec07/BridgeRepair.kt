package at.kocmana.aoc24.dec07

import at.kocmana.helper.Parser

fun main() {
    val result = parseInput("/dec07/bridge-repair.txt").asSequence()
        .map { it to assertCalculation(it) }
        .filter { it.second == true }
        .sumOf { it.first.result }
    println(result)
}

fun parseInput(filename: String): List<Calculation> =
    Parser().readFile(filename)
        .map { it.toCalculation() }

fun String.toCalculation(): Calculation {
    val result = this.substringBefore(": ")
        .toLong()
    val numbers = this.substringAfter(": ")
        .split(" ")
        .map { it.toLong() }
    return Calculation(result, numbers)
}

fun calculate(result: Long, current: Long, numbers: List<Long>, nextOperand: MathOperand): List<Long> {
    if (result < current) {
        return listOf()
    }
    if (numbers.isEmpty()) {
        return listOf(current)
    }
    val currentResult = nextOperand.calculation.invoke(current, numbers.first())
    val currentList = numbers.drop(1)
    return MathOperand.entries.asSequence()
        .map { calculate(result, currentResult, currentList, it) }
        .flatten()
        .toList()
}

fun assertCalculation(calculation: Calculation) =
    MathOperand.entries.asSequence()
        .map { calculate(calculation.result, 0, calculation.numbers, it) }
        .flatten()
        .contains(calculation.result)


data class Calculation(val result: Long, val numbers: List<Long>)

enum class MathOperand(val calculation: (Long, Long) -> Long) {
    ADD({ i, j -> i + j }),
    MULTIPLY({ i, j -> i * j }),
    COMBINE({ i, j -> "$i$j".toLong() });
}