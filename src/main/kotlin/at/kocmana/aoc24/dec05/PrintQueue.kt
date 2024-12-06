package at.kocmana.aoc24.dec05

import at.kocmana.helper.Parser

fun main() {
    val file = Parser().readFile("/dec05/pages.txt")
    val pageInstructions = parsePageInstructions(file)
    val pages = parsePages(file)

    val predicates = pageInstructions.asSequence()
        .map { instruction -> { pages: List<Int> -> isInCorrectOrder(instruction, pages) } }

    val result = pages.asSequence()
        .filter { pages -> predicates.all { it(pages) } }
        .map(::getMiddlePageNumber)
        .sum()

    println(result)
}

fun parsePageInstructions(input: List<String>) =
    input.mapNotNull(::parsePageInstructions)

fun parsePages(input: List<String>) =
    input.mapNotNull(::parsePages)

val pagesOrderPattern = """(?<first>\d+)\|(?<second>\d+)""".toRegex()
val pagesPattern = """(\d+,)+(\d+)""".toRegex()

fun parsePageInstructions(input: String) =
    pagesOrderPattern.find(input)
        ?.let { result -> result.groups["first"]!!.value.toInt() to result.groups["second"]!!.value.toInt() }

fun parsePages(input: String) =
    pagesPattern.find(input)
        ?.value
        ?.split(",")
        ?.mapNotNull { str -> str.trim().toIntOrNull() }

fun isInCorrectOrder(instruction: Pair<Int, Int>, pages: List<Int>): Boolean {
    if (!pages.containsAll(instruction.toList())) {
        return true
    }
    return pages.indexOf(instruction.first) < pages.indexOf(instruction.second)
}

fun getMiddlePageNumber(pages: List<Int>) =
    pages[pages.size / 2]