package at.kocmana.aoc24.dec06

fun main() {
    val grid = Grid("/dec06/guardgallivant.txt", '#')

    val positions = mutableSetOf<Pair<Int, Int>>()
    val actor = grid.actors.first()
    do {
        positions.add(actor.position)
        val position = grid.determineNextPositionForActor(actor)
        if (grid.isBlocked(position)) {
            actor.direction = actor.direction.turn()
        }
        grid.moveActor(actor, actor.direction)
    } while (!grid.isOutOfBounds(position))

    print(positions.size)
}