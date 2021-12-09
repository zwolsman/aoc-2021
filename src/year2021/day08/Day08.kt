package year2021.day08

import readInput

fun main() {

    val displays = listOf(
        0 to 6,
        1 to 2,
        2 to 5,
        3 to 5,
        4 to 4,
        5 to 5,
        6 to 6,
        7 to 3,
        8 to 7,
        9 to 6,
    )

    fun part1(input: List<String>): Int {
        val uniqueSegments = displays
            .groupBy { (_, segments) -> segments }
            .filter { it.value.size == 1 }

        return input.flatMap { line ->
            val (_, outputValue) = line.split("|")
            outputValue.split(" ")
        }.count {
            uniqueSegments.containsKey(it.length)
        }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("year2021/day08/test")
    check(part1(testInput) == 26) { "result = ${part1(testInput)}" }
//    check(part2(testInput) == 1) { "result = ${part2(testInput)}" }

    val input = readInput("year2021/day08/input")
    println(part1(input))
//    println(part2(input))
}
