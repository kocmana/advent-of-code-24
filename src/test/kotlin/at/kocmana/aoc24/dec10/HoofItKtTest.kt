package at.kocmana.aoc24.dec10

import at.kocmana.helper.Board
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import org.junit.jupiter.api.Test


class HoofItKtTest {

    @Test
    fun shouldFindAllStartingPoints() {
        val board = Board("/dec10/HoofItSparse.txt")

        val actual = findStartingPoints(board).also { println(it) }

        assertSoftly {
            actual shouldHaveSize 1
            actual shouldContainExactly listOf(board.Position(3,0))
        }
    }
}