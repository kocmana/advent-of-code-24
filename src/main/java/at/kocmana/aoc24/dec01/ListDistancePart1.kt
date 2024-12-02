package at.kocmana.aoc24.dec01

import at.kocmana.helper.Parser
import kotlin.math.abs

fun main() {
    val input = Parser().readFile("/dec01/hysteria.txt")
    val parsedInput = parseInput(input)
    println(calculateTotalDistance(parsedInput.first, parsedInput.second))
}

fun parseInput(input: List<String>): Pair<List<Int>, List<Int>> =
    input.asSequence()
        .map {
            val (leftNumber, rightNumber) = it.split("   ")
            Integer.valueOf(leftNumber) to Integer.valueOf(rightNumber)
        }
        .unzip()


fun getDistances(first: List<Int>, second: List<Int>): List<Int> {
    val firstSorted = first.sorted()
    val secondSorted = second.sorted()

    return firstSorted.mapIndexed { i, value -> abs(value - secondSorted[i]) }
}

fun calculateTotalDistance(first: List<Int>, second: List<Int>) =
    getDistances(first, second).sum()