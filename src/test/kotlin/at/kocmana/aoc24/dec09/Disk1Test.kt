package at.kocmana.aoc24.dec09

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Disk1Test {

    @Test
    fun test() {
        val input = "2333133121414131402"
        val expectedOutput = listOf(
            0,
            0,
            null,
            null,
            null,
            1,
            1,
            1,
            null,
            null,
            null,
            2,
            null,
            null,
            null,
            3,
            3,
            3,
            null,
            4,
            4,
            null,
            5,
            5,
            5,
            5,
            null,
            6,
            6,
            6,
            6,
            null,
            7,
            7,
            7,
            null,
            8,
            8,
            8,
            8,
            9,
            9
        )

        val actualResult = Disk(input)

        assertEquals(expected = expectedOutput, actual = actualResult.disk)
    }

    @Test
    fun compact() {
        val input = "2333133121414131402"
        val disk = Disk(input)

        disk.compact()
        assertEquals("0099811188827773336446555566..............", disk.toString())
        println(disk)
    }

    @Test
    fun checksum() {
        val input = "2333133121414131402"
        val disk = Disk(input)
        disk.compact()

        val actualResult = disk.generateChecksum()

        assertEquals(1928, actualResult)
    }

}