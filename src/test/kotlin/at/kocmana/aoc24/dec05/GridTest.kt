package at.kocmana.aoc24.dec05

import at.kocmana.aoc24.dec06.Direction
import at.kocmana.aoc24.dec06.Grid
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GridTest {

    @Test
    fun initialize() {
        val actualGrid = Grid("/dec06/guardgallivant.txt", '#')

        assertEquals('.', actualGrid.getField(0 to 0))
        assertEquals('#', actualGrid.getField(4 to 0))
    }

    @Test
    fun isBlocked() {
        val actualGrid = Grid("/dec06/guardgallivant.txt", '#')

        assertTrue(actualGrid.isBlocked(4 to 0))
        assertFalse(actualGrid.isBlocked(4 to 1))
    }

    @Test
    fun isOutOfBounds() {
        val actualGrid = Grid("/dec06/guardgallivant.txt", '#')

        assertTrue(actualGrid.isOutOfBounds(10 to 0))
        assertTrue(actualGrid.isOutOfBounds(0 to 10))
        assertFalse(actualGrid.isOutOfBounds(4 to 1))
    }

    @Test
    fun identifyActors() {
        val actualGrid = Grid("/dec06/guardgallivant.txt", '#')

        assertEquals(1, actualGrid.actors.size)
        val actor = actualGrid.actors.first()
        assertEquals(4 to 6, actor.position)
        assertEquals(Direction.UP, actor.direction)
    }

    @Test
    fun print() {
        val actualGrid = Grid("/dec06/guardgallivant.txt", '#')
        actualGrid.print()
    }


    @Test
    fun copy() {
        val initialGrid = Grid("/dec06/guardgallivant.txt", '#')

        val actualGrid = initialGrid.copyWithChangedField(0 to 0, '!')
        assertEquals('.', initialGrid.getField(0 to 0))
        assertEquals('!', actualGrid.getField(0 to 0))
    }
}