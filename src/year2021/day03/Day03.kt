package year2021.day03

import readInput

fun main() {

    fun part1(input: List<String>): Int {
        val (example) = input
        val threshold = input.size / 2
        val gammaCompare = { a: Int -> a > threshold }
        val epsilonCompare = { a: Int -> a < threshold }

        val counts = example
            .indices
            .map { index -> input.count { it[index] == '1' } }

        val gammaRate = counts
            .joinToString(separator = "") {
                if (gammaCompare(it)) "1" else "0"
            }
            .toInt(2)

        val epsilonRate = counts
            .joinToString(separator = "") {
                if (epsilonCompare(it)) "1" else "0"
            }
            .toInt(2)

        return gammaRate * epsilonRate
    }

    fun part2(input: List<String>): Int {
        val (example) = input
        val oxygenGeneratorRatingComparator = { high: List<String>, low: List<String> ->
            if (high.size > low.size || high.size == low.size)
                high
            else
                low
        }
        val co2ScrubberRatingComparator = { high: List<String>, low: List<String> ->
            if (high.size > low.size || high.size == low.size)
                low
            else
                high
        }

        val oxygenGeneratorRating = example
            .foldIndexed(input) { index, acc, _ ->
                val (high, low) = acc.partition { it[index] == '1' }
                if (acc.size == 1)
                    acc
                else
                    oxygenGeneratorRatingComparator(high, low)
            }
            .first()
            .toInt(2)

        val co2ScrubberRating = example
            .foldIndexed(input) { index, acc, _ ->
                val (high, low) = acc.partition { it[index] == '1' }
                if (acc.size == 1)
                    acc
                else
                    co2ScrubberRatingComparator(high, low)
            }
            .first()
            .toInt(2)

        return oxygenGeneratorRating * co2ScrubberRating
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("year2021/day03/test")
    check(part1(testInput) == 198) { "result = ${part1(testInput)}" }
    check(part2(testInput) == 230) { "result = ${part2(testInput)}" }

    val input = readInput("year2021/day03/input")
    check(part1(input) == 2967914) { "result = ${part1(input)}" }
    check(part2(input) == 7041258) { "result = ${part2(input)}" }
    println(part1(input))
    println(part2(input))
}
