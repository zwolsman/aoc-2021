package day06

import readInput

const val NEW_FISH = 8
const val RESET_FISH = 6
fun main() {
    fun simulateFish(input: List<Int>, days: Int): Long {
        val initialState = input.groupBy { it }.mapValues { (_, v) -> v.size.toLong() }
        val daysRange = 0 until days

        return daysRange
            .fold(initialState) { state, _ ->
                val newState = mutableMapOf<Int, Long>()
                state.forEach { (day, count) ->
                    val key = if (day == 0) {
                        newState[NEW_FISH] = count
                        RESET_FISH
                    } else {
                        day - 1
                    }

                    newState[key] = newState.getOrDefault(key, 0) + count
                }
                newState
            }
            .values
            .sum()
    }

    fun part1(input: List<String>): Long {
        val fish = input.map { it.toInt() }
        return simulateFish(fish, 80)
    }

    fun part2(input: List<String>): Long {
        val fish = input.map { it.toInt() }
        return simulateFish(fish, 256)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day06/test", ",")
    check(part1(testInput) == 5934L) { "result = ${part1(testInput)}" }
    check(part2(testInput) == 26984457539L) { "result = ${part2(testInput)}" }

    val input = readInput("day06/input", ",")
    check(part1(input) == 345793L)
    check(part2(input) == 1572643095893L)
    println(part1(input))
    println(part2(input))
}
