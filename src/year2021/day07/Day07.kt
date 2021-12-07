package year2021.day07

import readInput
import kotlin.math.abs

private typealias Crab = Int

fun main() {

    fun rollup(n: Int, memoize: MutableMap<Int, Int>): Int {
        if (n == 0)
            return 0
        return memoize.getOrPut(n) { n + rollup(n - 1, memoize) }
    }

    fun bestHorizontal(crabs: List<Crab>, distanceFn: (Int, Int) -> Int): Int {
        val positions = crabs.groupBy { it }.mapValues { (_, crabs) -> crabs.size }
        val horizontalRange = 0 until positions.maxOf { it.key }
        return horizontalRange.minOf { target ->
            positions.entries.sumOf { (position, count) ->
                distanceFn(position, target) * count
            }
        }
    }

    fun part1(input: List<String>): Int {
        val crabs = input.map { it.toInt() }
        return bestHorizontal(crabs) { position, target -> abs(position - target) }
    }

    fun part2(input: List<String>): Int {
        val crabs = input.map { it.toInt() }
        val memoize = mutableMapOf<Int, Int>()
        return bestHorizontal(crabs) { position, target ->
            val distance = abs(position - target)
            rollup(distance, memoize)
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("year2021/day07/test", ",")
    check(part1(testInput) == 37) { "result = ${part1(testInput)}" }
    check(part2(testInput) == 168) { "result = ${part2(testInput)}" }

    val input = readInput("year2021/day07/input", ",")
    println(part1(input))
    println(part2(input))
}
