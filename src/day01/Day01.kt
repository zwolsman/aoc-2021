package day01

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val scans = input.map(String::toInt)
        var (prev) = scans
        var increases = 0
        scans.forEach { value ->
            if (value > prev)
                increases++
            prev = value
        }
        return increases
    }

    fun part2(input: List<String>): Int {
        val scans = input
            .map(String::toInt)
            .windowed(size = 3, step = 1, partialWindows = false)
            .map { it.sum() }
        var (prev) = scans
        var increases = 0
        scans.forEach { value ->
            if (value > prev)
                increases++
            prev = value
        }
        return increases
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day01/test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("day01/input")
    println(part1(input))
    println(part2(input))
}
