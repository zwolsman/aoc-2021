package day06

import readInput

const val NEW_FISH = 8
const val RESET_FISH = 6
fun main() {
    fun simulateFish(initialState: List<Int>, days: Int): Long {
        var state = initialState.groupBy { it }.mapValues { (_, v) -> v.size.toLong() }

        for (day in 0 until days) {
            val newState = mutableMapOf<Int, Long>()
            for ((age, count) in state) {
                if (age == 0) {
                    newState[NEW_FISH] = count
                    newState[RESET_FISH] = newState.getOrDefault(RESET_FISH, 0) + count
                } else {
                    val key = age - 1
                    newState[key] = newState.getOrDefault(key, 0) + count
                }
                state = newState
            }
        }

        return state.values.sum()
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
    check(part2(testInput) == 26984457539) { "result = ${part2(testInput)}" }

    val input = readInput("day06/input", ",")
    println(part1(input))
    println(part2(input))
}
