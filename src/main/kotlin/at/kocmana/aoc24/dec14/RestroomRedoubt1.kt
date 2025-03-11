package at.kocmana.aoc24.dec14

import at.kocmana.helper.Parser

const val ITERATIONS = 100L
val BOARD_SIZE = 101 to 103

fun main() {
    val agents = Parser().readFile("/dec14/restroom-redoubt.txt")
        .map(Agent::fromString)
    println(solve(agents))
}

fun solve(agents: List<Agent>): Int {
    val board = Board(BOARD_SIZE.first, BOARD_SIZE.second)

    agents.forEach {
        it.apply {
            multiplyVector(ITERATIONS)
            move()
            fitToBoard(BOARD_SIZE.first, BOARD_SIZE.second)
        }
    }
    return agents.asSequence()
        .map { it.position }
        .groupBy { board.returnQuadrant(it) }
        .filterKeys { it != null }
        .map { it.value.count() }
        .reduce { a, b -> a * b }
}

data class Agent(var position: Position, var vector: Vector) {

    companion object {
        fun fromString(string: String): Agent {
            val pattern = """^p=(?<posX>-*\d+),(?<posY>-*\d+) v=(?<vecX>-*\d+),(?<vecY>-*\d+)$""".toPattern()
            val matcher = pattern.matcher(string)
            matcher.find()
            return Agent(
                Position(matcher.group("posX").toLong(), matcher.group("posY").toLong()),
                Vector(matcher.group("vecX").toLong(), matcher.group("vecY").toLong())
            )
        }
    }

    fun multiplyVector(factor: Long): Unit {
        this.vector *= factor
    }

    fun move() {
        this.position += vector
    }

    fun fitToBoard(boardWidth: Int, boardHeight: Int): Unit {
        position.x = position.x.mod(boardWidth.toLong())
        position.y = position.y.mod(boardHeight.toLong())
    }

}
