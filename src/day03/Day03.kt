package day03

import readInput

fun main() {

    fun part1(input: List<String>): Int {
        val count = mutableMapOf<Int, Int>()
        for (line in input) {
            for ((index, v) in line.withIndex()) {
                count[index] = count.getOrDefault(index, 0) + v.toString().toInt()
            }
        }

        val threshold = input.size / 2

        val gammaRate = (0 until input[0].length).joinToString(separator = "") { index ->
            if (count[index]!! > threshold)
                "1"
            else
                "0"
        }
        val epsilonRate = (0 until input[0].length).joinToString(separator = "") { index ->
            if (count[index]!! < threshold)
                "1"
            else
                "0"
        }

        return gammaRate.toInt(2) * epsilonRate.toInt(2)
    }

    fun part2(input: List<String>): Int {
        fun oxygenGeneratorRating(): Int {
            var selection = input
            for (index in input[0].indices) {
                println(index)
                println(selection)
                println()
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
    println(part1(input))
    println(part2(input))
}
