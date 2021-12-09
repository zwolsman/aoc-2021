package year2021.day08

import readInput

fun main() {

    val segments = mapOf(
        0 to "acfgeb",
        1 to "cf",
        2 to "acdeg",
        3 to "acdfg",
        4 to "bcdf",
        5 to "abdfg",
        6 to "abdfge",
        7 to "acf",
        8 to "acdfgeb",
        9 to "acdfgb"
    )

    fun part1(input: List<String>): Int {
        val x = input.map { line ->
            val (uniqueSignalPattern, outputValue) = line.split("|")
            uniqueSignalPattern to outputValue
        }


        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("year2021/day08/test")
    check(part1(testInput) == 1) { "result = ${part1(testInput)}" }
    check(part2(testInput) == 1) { "result = ${part2(testInput)}" }

    val input = readInput("year2021/day08/input")
    println(part1(input))
    println(part2(input))
}
