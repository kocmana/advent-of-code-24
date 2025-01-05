package at.kocmana.aoc24.dec09

import at.kocmana.helper.Parser

fun main() {
    val input = Parser().readFile("/dec09/disk-fragmenter.txt").first()
    val disk = Disk(input)
    disk.compact()
    println(disk.generateChecksum())
}

class Disk(val disk: MutableList<Int?>) {

    constructor (string: String) : this(disk = mutableListOf<Int?>()) {
        val list = mutableListOf<Int>()
        val numbers = string.map(Char::toString)
            .map(String::toInt)
            .mapIndexed(::mapElement)
    }

    fun compact() {
        while(isNotFullyCompact()) {
            val firstNullIndex = disk.indexOfFirst { it == null }
            val lastNonNullIndex = disk.indexOfLast { it != null }
            disk[firstNullIndex] = disk[lastNonNullIndex].also { disk[lastNonNullIndex] = disk[firstNullIndex] }
        }
    }

    fun isNotFullyCompact() =
        disk.indexOfLast { it != null } > disk.indexOfFirst{it == null}

    fun generateChecksum() =
        disk.mapIndexed { index, number -> index * (number ?: 0) }.sumOf(Int::toLong)

    override fun toString() =
        disk.joinToString(separator = "", transform = {number -> number?.toString()?:"."})

    private fun mapElement(index: Int, number: Int) =
        if (index % 2 == 0) appendWrittenSpace(number, index / 2) else appendEmptySpace(number)

    private fun appendEmptySpace(length: Int) =
        repeat(length) { disk.add(null) }

    private fun appendWrittenSpace(length: Int, index: Int) =
        repeat(length) { disk.add(index) }
}