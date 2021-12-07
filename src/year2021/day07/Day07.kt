package year2021.day07

import readInput
import java.lang.Math.abs

fun main() {

    fun part1(input: List<String>): Int {
        val crabs = input.map { it.toInt() }.sorted()
        val fuelMap = mutableMapOf<Int, Int>()

        for (target in 0 until crabs.maxOf { it }) {
            fuelMap[target] = crabs.fold(0) { acc, crab ->
                acc + abs(crab - target)
            }
        }
        return fuelMap.minOf { (_, costs) -> costs }
    }

    fun part2(input: List<String>): Int {
        val crabs = input.map { it.toInt() }.sorted()
        val fuelMap = mutableMapOf<Int, Int>()

        for (target in 0 until crabs.maxOf { it }) {
            fuelMap[target] = crabs.fold(0) { acc, crab ->
                val distance = abs(crab - target)
                val addition = (0..distance).reduceOrNull { j, i -> j + i } ?: 0
                acc + addition
            }
        }
        return fuelMap.minOf { (_, costs) -> costs }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("year2021/day07/test", ",")
    check(part1(testInput) == 37) { "result = ${part1(testInput)}" }
    check(part2(testInput) == 168) { "result = ${part2(testInput)}" }

    val input = readInput("year2021/day07/input", ",")
    println(part1(input))
    println(part2(input))
}
