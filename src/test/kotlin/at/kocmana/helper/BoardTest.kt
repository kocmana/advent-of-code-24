package at.kocmana.helper

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

val testBoard = Board("/helper/test-matrix.txt")

class BoardTest {
    @Test
    fun findAllPositionsWhere() {
        val actual = testBoard.findAllPositionsWhere { it == "1" }

        assertSoftly {
            actual shouldHaveSize 2
            actual shouldContainExactly listOf(testBoard.Position(1, 0), testBoard.Position(5, 4))
        }
    }

    @Test
    fun isValidPosition_returnsTrue() {
        testBoard.isValidPosition(testBoard.Position(0, 0)) shouldBe true
        testBoard.isValidPosition(testBoard.Position(5, 5)) shouldBe true
    }

    @Test
    fun isValidPosition_returnsFalse() {
        testBoard.isValidPosition(testBoard.Position(-1, -1)) shouldBe false
        testBoard.isValidPosition(testBoard.Position(6, 6)) shouldBe false
    }
}