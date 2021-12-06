package day06

import readInput

fun main() {

    fun part1(input: List<String>): Int {
        var fish = input.map { it.toInt() }
        for (day in 0 until 80) {
            val a = fish
                .map { it - 1 }
                .toMutableList()

            val newFish = a.count { it == -1 }
            a.removeAll { it == -1 }

            fish = a + List(newFish) { 6 } + List(newFish) { 8 }
        }

        return fish.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day06/test", ",")
    check(part1(testInput) == 5934) { "result = ${part1(testInput)}" }
//    check(part2(testInput) == 1) { "result = ${part2(testInput)}" }

    val input = readInput("day06/input", ",")
    println(part1(input))
//    println(part2(input))
}
