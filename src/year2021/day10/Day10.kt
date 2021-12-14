package year2021.day10

import readInput

class IllegalCharacterException(expected: Char?, found: Char, val points: Int) :
    Exception("Expected $expected, but found $found instead")

fun main() {

    val pairs = mapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}',
        '<' to '>',
    )

    fun String.toNavigationSyntax(): String? {
        val points = mapOf(
            ')' to 3,
            ']' to 57,
            '}' to 1197,
            '>' to 25137,
        )

        val result = runningFold("") { acc, c ->
            if (c in pairs.keys)
                return@runningFold acc + c

            val last = acc.lastOrNull()
            val expected = pairs[last]
            if (c == expected)
                acc.dropLast(1)
            else
                throw IllegalCharacterException(expected, c, points[c]!!)
        }
        return result.lastOrNull()
    }

    fun part1(input: List<String>) =
        input.sumOf {
            val result = it.runCatching { it.toNavigationSyntax() }
            when (val exception = result.exceptionOrNull()) {
                is IllegalCharacterException -> exception.points
                else -> 0
            }
        }

        return missing.map { (key, amount) -> points[key]!! * amount }.sum()
    }

        return leftOvers.sorted().elementAt(leftOvers.size / 2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("year2021/day10/test")
    check(part1(testInput) == 26397) { "result = ${part1(testInput)}" }
//    check(part2(testInput) == 1) { "result = ${part2(testInput)}" }

    val input = readInput("year2021/day10/input")
    println(part1(input))
//    println(part2(input))
}
