package at.kocmana.aoc24.dec03

import at.kocmana.helper.Parser

fun main() {
    val input = Parser().readFile("/dec03/multiplier.txt").joinToString (separator = "")
    val result = parseWithEnabler(input)
    println(result)
}

fun parseWithEnabler(instructions: String): Long {
    val instructionPattern = """mul\((?<firstDigit>\d+),(?<secondDigit>\d+)\)|(?<do>do\(\))|(?<dont>don't\(\))"""
        .toRegex()

    val matches = instructionPattern.findAll(instructions).toList()

    var sum = 0L
    var enabled = true
    for (match in matches) {
        when(match.value){
            "do()" -> enabled = true
            "don't()" -> enabled = false
            else -> if(enabled) sum += multiplyMatch(match)
        }
    }
    return sum
}

fun multiplyMatch(match: MatchResult) =
    match.groups["firstDigit"]!!.value.toLong() * match.groups["secondDigit"]!!.value.toLong()
