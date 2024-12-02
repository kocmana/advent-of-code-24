package at.kocmana.aoc24.dec02

import at.kocmana.helper.Parser

fun main() {
    val records = Parser().readToIntLists("/dec02/report.txt", " ")
        .map(::Report)

    val numberOfSafeRecords = records.count(Report::assessIfSafe)

    println("Number of Safe Records: $numberOfSafeRecords")
}
