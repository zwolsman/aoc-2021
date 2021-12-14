package year2021.day10

import readInput

fun main() {

    val pairs = mapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}',
        '<' to '>',
    )

    val points = mapOf(
        ')' to 3,
        ']' to 57,
        '}' to 1197,
        '>' to 25137
    )

    fun part1(input: List<String>): Int {

        val missing = mutableMapOf<Char, Int>()
        root@ for (line in input) {
            val stack = mutableListOf<Char>()

            for (c in line) {
                if (c in pairs.keys)
                    stack += c
                if (c in pairs.values) {
                    val last = stack.removeLastOrNull()
                    val expected = pairs[last]
                    if (expected != c) {
                        missing[c] = missing.getOrDefault(c, 0) + 1
                        continue@root
                    }
                }

            }
        }

        return missing.map { (key, amount) -> points[key]!! * amount }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("year2021/day10/test")
    check(part1(testInput) == 26397) { "result = ${part1(testInput)}" }
//    check(part2(testInput) == 1) { "result = ${part2(testInput)}" }

    val input = readInput("year2021/day10/input")
    println(part1(input))
//    println(part2(input))
}
