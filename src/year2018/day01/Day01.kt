package year2018.day01

import readInput

fun main() {

    fun part1(input: List<String>): Int {
        return input.sumOf { it.trim().toInt() }
    }

    fun part2(input: List<String>): Int {
        var history = setOf<Int>()
        val changes = input.map { it.trim().toInt() }
        var initial = 0
        while (true) {
            initial = changes.fold(initial) { acc, i ->
                if (history.contains(acc))
                    return acc
                else
                    history = history + acc
                acc + i
            }
        }
        error("no double frequency found")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("year2018/day01/test", ",")
    check(part1(testInput) == 1) { "result = ${part1(testInput)}" }
    check(part2(testInput) == 14) { "result = ${part2(testInput)}" }

    val input = readInput("year2018/day01/input")
    println(part1(input))
    println(part2(input))
}
