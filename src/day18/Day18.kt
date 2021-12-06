package day18

import readInput

fun main() {

    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day18/test")
    check(part1(testInput) == 1) { "result = ${part1(testInput)}" }
    check(part2(testInput) == 1) { "result = ${part2(testInput)}" }

    val input = readInput("day18/input")
    println(part1(input))
    println(part2(input))
}