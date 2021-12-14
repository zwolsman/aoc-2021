package year2021.day10

import readInput

class IllegalCharacterException(expected: Char?, val found: Char) :
    Exception("Expected $expected, but found $found instead")

fun main() {

    val pairs = mapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}',
        '<' to '>',
    )

    fun String.toNavigationSyntax(): String? {
        val result = runningFold("") { acc, c ->
            if (c in pairs.keys)
                return@runningFold acc + c

            val last = acc.lastOrNull()
            val expected = pairs[last]
            if (c == expected)
                acc.dropLast(1)
            else
                throw IllegalCharacterException(expected, c)
        }
        return result.lastOrNull()
    }

    fun part1(input: List<String>): Int {
        val points = mapOf(
            ')' to 3,
            ']' to 57,
            '}' to 1197,
            '>' to 25137,
        )
        return input.sumOf {
            val result = it.runCatching { it.toNavigationSyntax() }
            when (val exception = result.exceptionOrNull()) {
                is IllegalCharacterException -> points[exception.found]
                else -> 0
            }
        }
    }

    fun part2(input: List<String>): Long {
        val points = mapOf(
            ')' to 1,
            ']' to 2,
            '}' to 3,
            '>' to 4,
        )
        val result = input
            .mapNotNull {
                val result = it.runCatching { it.toNavigationSyntax() }
                result.getOrNull()
            }
            .map { incomplete ->
                incomplete
                    .reversed()
                    .map { points[pairs[it]] }
            }
            .map {
                it
                    .requireNoNulls()
                    .fold(0L) { acc, i ->
                        acc * 5 + i
                    }
            }

        return result.sorted().elementAt(result.size / 2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("year2021/day10/test")
    check(part1(testInput) == 26397) { "result = ${part1(testInput)}" }
    check(part2(testInput) == 288957L) { "result = ${part2(testInput)}" }

    val input = readInput("year2021/day10/input")
    println(part1(input))
    println(part2(input))
}
