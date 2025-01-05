package at.kocmana.aoc24.dec09

import at.kocmana.helper.Parser
import at.kocmana.helper.cutAndInsertPartialList

fun main() {
    val input = Parser().readFile("/dec09/disk-fragmenter.txt").first()
    val disk = Disk2(input)
    disk.compact()
    println(disk.generateChecksum())
}

class Disk2(val disk: MutableList<Int?>) {

    val partitions: MutableList<PartitionInformation> = mutableListOf()

    constructor (string: String) : this(disk = mutableListOf<Int?>()) {
        val numbers = string.map(Char::toString)
            .map(String::toInt)
            .mapIndexed(::mapElement)
    }

    fun compact() {
        for (partitionInformation in partitions.reversed()) {
            val suitableSpaceIndex = getFirstSuitableEmptySpaceOrNull(partitionInformation.length) ?: continue
            if(suitableSpaceIndex > partitionInformation.startingIndex) continue

            disk.cutAndInsertPartialList(
                fromIndex = partitionInformation.startingIndex,
                length = partitionInformation.length,
                targetIndex = suitableSpaceIndex
            )
        }
    }

    fun generateChecksum() =
        disk.mapIndexed { index, number -> index * (number ?: 0) }.sumOf(Int::toLong)

    override fun toString() =
        disk.joinToString(separator = "", transform = { number -> number?.toString() ?: "." })

    private fun mapElement(index: Int, number: Int) =
        if (index % 2 == 0) appendWrittenSpace(number, index / 2) else appendEmptySpace(number)

    private fun appendEmptySpace(length: Int) =
        repeat(length) { disk.add(null) }

    private fun appendWrittenSpace(length: Int, index: Int) {
        partitions += PartitionInformation(index, disk.size, length)
        repeat(length) { disk.add(index) }
    }

    private fun getFirstSuitableEmptySpaceOrNull(length: Int) =
        disk.windowed(length)
            .indexOfFirst { window -> window.all { it == null } }
            .takeIf { it != -1 }
}

data class PartitionInformation(val partitionId: Int, val startingIndex: Int, val length: Int)