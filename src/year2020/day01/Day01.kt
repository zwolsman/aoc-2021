package year2020.day01

import readInput

fun main() {

    fun part1(input: List<String>): Int {
        val expenseReport = input.map { it.toInt() }
        for (i in expenseReport) {
            for (j in expenseReport) {
                if (i + j == 2020)
                    return i * j
            }
        }

        error("no pair found")
    }

    fun part2(input: List<String>): Int {
        val expenseReport = input.map { it.toInt() }
        for (i in expenseReport) {
            for (j in expenseReport) {
                for (k in expenseReport) {
                    if (i + j + k == 2020)
                        return i * j * k
                }
            }
        }

        error("no triplet found")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("year2020/day01/test")
    check(part1(testInput) == 514579) { "result = ${part1(testInput)}" }
    check(part2(testInput) == 241861950) { "result = ${part2(testInput)}" }

    val input = readInput("year2020/day01/input")
    println(part1(input))
    println(part2(input))
}
