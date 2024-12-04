package at.kocmana.aoc24.dec01

import at.kocmana.helper.Parser

fun main() {
    val input = Parser().readFile("/dec01/hysteria.txt")
    val parsedInput = parseInput(input)
    println(calculateTotalSimilarities(parsedInput.first, parsedInput.second))
}

fun getSimilarities(first: List<Int>, second: List<Int>) =
    first.map { value -> value * second.count { it == value } }

fun calculateTotalSimilarities(first: List<Int>, second: List<Int>) =
    getSimilarities(first, second).sum()
