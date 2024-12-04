package at.kocmana.aoc24.dec03

import at.kocmana.helper.Parser

fun main() {
    val input = Parser().readFile("/dec03/multiplier.txt").joinToString (separator = "")
    println(parse(input))
}

fun parse(instructions: String): Long {
    val instructionPattern = """mul\((?<firstDigit>\d+),(?<secondDigit>\d+)\)""".toRegex()

    return instructionPattern.findAll(instructions)
        .map{ it.groups["firstDigit"]!!.value.toLong() * it.groups["secondDigit"]!!.value.toLong() }
        .sum()
}
