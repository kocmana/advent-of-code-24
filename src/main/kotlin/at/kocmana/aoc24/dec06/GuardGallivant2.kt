package at.kocmana.aoc24.dec06

fun main() {
    val grid = Grid("/dec06/guardgallivant.txt", '#')
    val possiblePositions = initialRun(grid)

    val result = possiblePositions.asSequence()
        .map { grid.copyWithChangedField(it, '#') }
        .filter { isCircle(it) }
        .count()

    println(result)
}

fun initialRun(grid: Grid): Set<Pair<Int, Int>> {
    val actor = grid.actors.first()
    val positions = mutableSetOf<Pair<Int, Int>>()

    do {
        positions.add(actor.position)
        val position = grid.determineNextPositionForActor(actor)
        if (grid.isBlocked(position)) {
            actor.direction = actor.direction.turn()
        }
        grid.moveActor(actor, actor.direction)
    } while (!grid.isOutOfBounds(position))
    return positions
}

fun isCircle(grid: Grid): Boolean {
    val actor = grid.actors.first()
    val positions = mutableSetOf<Pair<Direction, Pair<Int, Int>>>()

    while (!positions.contains(actor.direction to actor.position)) {
        positions.add(actor.direction to actor.position)
        val position = grid.determineNextPositionForActor(actor)
        if (grid.isOutOfBounds(position)) {
            return false
        }
        if (grid.isBlocked(position)) {
            actor.direction = actor.direction.turn()
        }
        grid.moveActor(actor, actor.direction)
    }
    return true
}