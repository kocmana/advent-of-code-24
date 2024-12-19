package at.kocmana.aoc24.dec06

import at.kocmana.helper.Parser
import at.kocmana.helper.transposeArray

class Grid {

    private val blocker: Char

    private var fields: Array<Array<Char>>

    private val mask: Array<Array<Char>>

    val actors: List<Actor>

    constructor(file: String, blocker: Char) {
        this.blocker = blocker
        this.fields = initializeFieldsFromFile(file)
        this.mask = fields.map { it.map { ' ' }.toTypedArray() }
            .toTypedArray()
        this.actors = identifyActors()
    }

    private constructor(fields: Array<Array<Char>>, blocker: Char) {
        this.blocker = blocker
        this.fields = fields
        this.mask = fields.map { it.map { ' ' }.toTypedArray() }
            .toTypedArray()
        this.actors = identifyActors()
    }

    private fun initializeFieldsFromFile(file: String): Array<Array<Char>> =
        Parser().readFileTo2dArray(file)
            .map { innerArray ->
                innerArray.flatMap { it.toCharArray().toList() }.toTypedArray()
            }
            .toTypedArray()
            .let { transposeArray(it) }

    inner class Actor(var position: Pair<Int, Int>, var direction: Direction) {
        fun move(direction: Direction) {
            position = direction.move(position)
        }
    }

    private fun identifyActors(): List<Actor> {
        return fields.flatMapIndexed { columnId, column ->
            column.mapIndexedNotNull { rowId, char ->
                if (isDirection(fields[columnId][rowId])) {
                    Actor(columnId to rowId, Direction.fromChar(char))
                } else {
                    null
                }
            }
        }
    }

    fun size() =
        fields.size

    fun determineNextPositionForActor(actor: Actor) =
        actor.direction.move(actor.position)

    fun moveActor(actor: Actor, direction: Char): Boolean =
        moveActor(actor, Direction.fromChar(direction))

    fun moveActor(actor: Actor, direction: Direction): Boolean {
        val fieldToMoveTo = determineNextPositionForActor(actor)
        if (isOutOfBounds(fieldToMoveTo) || isBlocked(fieldToMoveTo)) {
            return false
        }
        actor.move(direction)
        return true
    }

    fun isBlocked(position: Pair<Int, Int>) =
        !isOutOfBounds(position) &&
        this.fields[position.first][position.second] == blocker

    fun isOutOfBounds(position: Pair<Int, Int>) =
        position.first !in this.fields.indices
                || position.second !in this.fields[position.first].indices

    fun getField(position: Pair<Int, Int>) =
        fields[position.first][position.second]

    fun setField(position: Pair<Int, Int>, char: Char) {
        fields[position.first][position.second] = char
    }

    fun copyWithChangedField(field: Pair<Int, Int>, newValue: Char) =
        Grid(fields.map { it.copyOf() }.toTypedArray(), blocker).also { grid -> grid.setField(field, newValue) }

    fun print() {
        var fieldsToPrint = fields.clone()
        actors.forEach { actor -> fieldsToPrint[actor.position.first][actor.position.second] = actor.direction.marker }
        fieldsToPrint = transposeArray(fieldsToPrint)
        fieldsToPrint.forEach { print(it.joinToString(separator = "", postfix = "\n")) }
    }
}

val isDirection = { char: Char -> Direction.entries.any { char == it.marker } }

enum class Direction(val marker: Char) {
    UP('^'),
    DOWN('v'),
    RIGHT('>'),
    LEFT('<');

    companion object {
        fun fromChar(c: Char): Direction =
            entries.first { it.marker == c }
    }

    private fun modifier(): (Pair<Int, Int>) -> Pair<Int, Int> =
        when (this) {
            UP -> { position -> position.first to position.second - 1 }
            DOWN -> { position -> position.first to position.second + 1 }
            RIGHT -> { position -> position.first + 1 to position.second }
            LEFT -> { position -> position.first - 1 to position.second }
        }

    fun move(position: Pair<Int, Int>) =
        modifier().invoke(position)

    fun turn() =
        when (this) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
        }
}