package year2021.day02

import readInput

fun main() {
    data class Position(val horizontal: Int, val depth: Int, val aim: Int = 0)

    fun part1(input: List<String>): Int {
        val position = input
            .map { row ->
                val (cmd, num) = row.split(" ")
                cmd to num.toInt()
            }
            .map { (cmd, num) ->
                when (cmd) {
                    "forward" -> Position(num, 0)
                    "down" -> Position(0, num)
                    "up" -> Position(0, -num)
                    else -> error("$cmd not supported")
                }
            }
            .reduce { acc, current ->
                Position(
                    horizontal = acc.horizontal + current.horizontal,
                    depth = acc.depth + current.depth
                )
            }

        return position.horizontal * position.depth
    }

    fun part2(input: List<String>): Int {
        val position = input
            .map { row ->
                val (cmd, num) = row.split(" ")
                cmd to num.toInt()
            }
            .map { (cmd, num) ->
                when (cmd) {
                    "forward" -> Position(num, 0, 0)
                    "down" -> Position(0, 0, num)
                    "up" -> Position(0, 0, -num)
                    else -> error("$cmd not supported")
                }
            }
            .reduce { acc, current ->
                Position(
                    horizontal = acc.horizontal + current.horizontal,
                    depth = acc.depth + acc.aim * current.horizontal,
                    aim = acc.aim + current.aim,
                )
            }

        return position.horizontal * position.depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("year2021/day02/test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("year2021/day02/input")
    println(part1(input))
    println(part2(input))
}
