package at.kocmana.aoc24.dec09

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Disk2Test {

    @Test
    fun creation() {
        val input = "2333133121414131402"

        val actualResult = Disk2(input)

        assertEquals(expected = 10, actual = actualResult.partitions.size)
    }

    @Test
    fun compact() {
        val input = "2333133121414131402"
        val disk = Disk2(input)

        disk.compact()
        assertEquals("00992111777.44.333....5555.6666.....8888..", disk.toString())
        println(disk)
    }

    @Test
    fun checksum() {
        val input = "2333133121414131402"
        val disk = Disk2(input)
        disk.compact()

        val actualResult = disk.generateChecksum()

        assertEquals(2858, actualResult)
    }

}