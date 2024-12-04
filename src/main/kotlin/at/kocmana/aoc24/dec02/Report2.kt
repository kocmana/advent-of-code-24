package at.kocmana.aoc24.dec02

import at.kocmana.helper.Parser

fun main() {
    val records = Parser().readToIntLists("/dec02/report.txt", " ")
        .map(::Report)

    val numberOfSafeRecords = records.count(Report::assessIfSafe)

    val numberOfSafeRecordsWhenModified = records.asSequence()
        .filterNot (Report::assessIfSafe)
        .map (Report::generateMutations)
        .filter(::assertMutations)
        .count()

    println(
        "Number of Safe Records: $numberOfSafeRecords, " +
                "Unsafe Records: $numberOfSafeRecordsWhenModified, " +
                "total: ${numberOfSafeRecords + numberOfSafeRecordsWhenModified}"
    )
}

fun assertMutations(mutations: List<Report>) =
    mutations.any(Report::assessIfSafe)