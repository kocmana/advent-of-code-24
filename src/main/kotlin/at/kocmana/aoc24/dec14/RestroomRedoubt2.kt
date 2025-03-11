package at.kocmana.aoc24.dec14

import at.kocmana.helper.Parser

fun main() {
    val agents = Parser().readFile("/dec14/restroom-redoubt.txt")
        .map(Agent::fromString)
    solvePart2(agents)
}

fun solvePart2(agents: List<Agent>) {
    val board = Board(BOARD_SIZE.first, BOARD_SIZE.second)

    for (i in 1..10_000) {
        agents.forEach {
            it.move()
            it.fitToBoard(BOARD_SIZE.first, BOARD_SIZE.second)
        }
        agents.forEach { board.mark(it.position) }
        if (board.hasNoOverlappingRobots()) {
            println("ITERATION $i")
            board.draw()
            println("\n\n")
        }
        board.clear()
    }
}