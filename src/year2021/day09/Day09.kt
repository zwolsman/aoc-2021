package year2021.day09

import readInput

data class Coordinate(val x: Int, val y: Int) {
    operator fun plus(other: Coordinate) = Coordinate(x + other.x, y + other.y)
}

fun main() {
    val options = listOf(
        //left
        Coordinate(-1, 0),
        //right
        Coordinate(1, 0),
        //top
        Coordinate(0, -1),
        //below
        Coordinate(0, 1)
    )

    fun lowPoints(cave: Map<Coordinate, Int>): Map<Coordinate, Int> =
        cave.filter { (origin, height) ->
            options.mapNotNull {
                cave[origin + it]
            }
                .all { height < it }
        }

    fun readCave(input: List<String>): Map<Coordinate, Int> =
        input.flatMapIndexed { y, line ->
            line.mapIndexed { x, c ->
                Coordinate(x, y) to c.toString().toInt()
            }
        }.toMap()

    fun part1(input: List<String>): Int {
        val cave = readCave(input)
        return lowPoints(cave).values.sumOf { it + 1 }
    }

    fun part2(input: List<String>): Int {
        val cave = readCave(input)
        val origins = lowPoints(cave)

        fun basin(position: Pair<Coordinate, Int>): Set<Pair<Coordinate, Int>> {
            val (origin, height) = position

            val others = options
                .mapNotNull {
                    val target = origin + it
                    when (val result = cave[target]) {
                        null -> null
                        else -> target to result
                    }
                }
                .filter { (_, neighborHeight) ->
                    neighborHeight in (height + 1) until 9
                }
                .map(::basin)

            return setOf(position) + others.flatten()
        }

        val basins = origins.map { (coord, height) -> coord to height }.map(::basin)
        return basins.sortedBy { it.size }.takeLast(3).fold(1) { acc, set -> acc * set.size }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("year2021/day09/test")
    check(part1(testInput) == 15) { "result = ${part1(testInput)}" }
    check(part2(testInput) == 1134) { "result = ${part2(testInput)}" }

    val input = readInput("year2021/day09/input")
    println(part1(input))
    println(part2(input))
}
