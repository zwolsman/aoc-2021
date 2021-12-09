package year2021.day09

import readInput

fun main() {

    fun part1(input: List<String>): Int {
        val width = input.first().length
        val cave = input.flatMap { line -> line.split("").mapNotNull { height -> height.toIntOrNull() } }
        val options = listOf(-1, 1, -width, width)

        val lowPoints = cave
            .filterIndexed { index, height ->
                val adjacentLocations = options.mapNotNull { option -> cave.getOrNull(option + index) }

                adjacentLocations.all { height < it }
            }
        return lowPoints.sumOf { it + 1 }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("year2021/day09/test")
    check(part1(testInput) == 15) { "result = ${part1(testInput)}" }
//    check(part2(testInput) == 1) { "result = ${part2(testInput)}" }

    val input = readInput("year2021/day09/input")
    println(part1(input))
//    println(part2(input))
}
