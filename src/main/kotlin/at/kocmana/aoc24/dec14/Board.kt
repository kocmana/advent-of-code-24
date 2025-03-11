package at.kocmana.aoc24.dec14

class Board(width: Int, height: Int){

    private val board: MutableList<MutableList<Int>> = MutableList(height) { MutableList(width) { 0 } }

    private val borderX = width / 2
    private val borderY = height / 2

    fun returnQuadrant(position: Position) =
        when {
            (position.x < borderX && position.y < borderY) -> 1
            (position.x > borderX && position.y < borderY) -> 2
            (position.x < borderX && position.y > borderY) -> 3
            (position.x > borderX && position.y > borderY) -> 4
            else -> null
        }

    fun hasNoOverlappingRobots() =
        board.all { row -> row.none { it > 1 } }

    fun mark(position: Position) {
        this.board[position.y.toInt()][position.x.toInt()] += 1
    }

    fun draw() {
        board.forEach { println(it.joinToString(separator = "") { if (it > 0) it.toString() else " " }) }
    }

    fun clear() {
        board.forEach { it.fill(0) }
    }
}


data class Position(var x: Long, var y: Long) {
    operator fun plus(v: Vector) = Position(x + v.x, y + v.y)
}

data class Vector(val x: Long, val y: Long) {
    operator fun times(factor: Long) = Vector(x * factor, y * factor)
}