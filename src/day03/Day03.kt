package day03

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
        fun oxygenGeneratorRating(): Int {
            var selection = input
            for (index in input[0].indices) {
                if (selection.size == 1)
                    break
                val (a, b) = selection.partition { it[index] == '1' }
                if (a.size > b.size)
                    selection = a
                if (b.size > a.size)
                    selection = b
                if (a.size == b.size)
                    selection = a
            }
            return selection.first().toInt(2)
        }

        fun co2ScrubberRating(): Int {
            var selection = input
            for (index in 0 until 5) {
                if (selection.size == 1)
                    break
                val (a, b) = selection.partition { it[index] == '1' }
                if (a.size < b.size)
                    selection = a
                if (b.size < a.size)
                    selection = b
                if (a.size == b.size)
                    selection = b
            }
            return selection.first().toInt(2)
        }
        return oxygenGeneratorRating() * co2ScrubberRating()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day03/test")
    check(part1(testInput) == 198) { "result = ${part1(testInput)}" }
    check(part2(testInput) == 230) { "result = ${part2(testInput)}" }

    val input = readInput("day03/input")
    check(part1(input) == 2967914) { "result = ${part1(input)}" }
    check(part2(input) == 7041258) { "result = ${part2(input)}" }
    println(part1(input))
    println(part2(input))
}
