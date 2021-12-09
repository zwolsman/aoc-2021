package year2021.day08

import readInput
import kotlin.math.pow

data class Display(val value: Int, val segments: Set<Char>)
data class Entry(val signalPatterns: List<Set<Char>>, val outputSignal: List<Set<Char>>)

fun Display(value: Int, input: String) = Display(value, input.toSet())
fun Entry(note: String) = Entry(
    signalPatterns = note.substringBefore(" |").split(" ").map { it.toSet() },
    outputSignal = note.substringAfter("| ").split(" ").map { it.toSet() },
)

fun main() {

    val displays = listOf(
        "abcefg",
        "cf",
        "acdeg",
        "acdfg",
        "bcdf",
        "abdfg",
        "abdefg",
        "acf",
        "abcdefg",
        "abcdfg",
    ).mapIndexed(::Display)

    val uniqueDisplays = displays
        .groupBy { it.segments.size }
        .filter { it.value.size == 1 }
        .flatMap { it.value }

    fun part1(input: List<String>): Int {
        return input
            .map(::Entry)
            .flatMap { it.outputSignal }
            .count { signalPattern -> uniqueDisplays.any { it.segments.size == signalPattern.size } }
    }

    fun part2(input: List<String>): Int {
        return input
            .map(::Entry)
            .map { (signalPatterns, outputSignal) ->
                val displays = signalPatterns
                    .sortedBy { it.size }
                    .fold(listOf<Display>()) { acc, segment ->
                        val displayMap = acc.associateBy { it.value }
                        val uniqueDisplay = uniqueDisplays.firstOrNull { it.segments.size == segment.size }
                        val display = if (uniqueDisplay != null)
                            Display(uniqueDisplay.value, segment)
                        else {
                            val num = when {
                                segment.size == 5 && displayMap[1]?.segments?.intersect(segment)?.size == 2 -> 3
                                segment.size == 5 && displayMap[4]?.segments?.intersect(segment)?.size == 2 -> 2
                                segment.size == 5 -> 5
                                segment.size == 6 && displayMap[4]?.segments?.intersect(segment)?.size == 4 -> 9
                                segment.size == 6 && displayMap[1]?.segments?.intersect(segment)?.size == 2 -> 0
                                segment.size == 6 -> 6
                                else -> error("couldn't map number, $segment")
                            }
                            Display(num, segment)
                        }

                        acc + display
                    }.associateBy { it.segments }

                val output = outputSignal.map { displays[it]?.value ?: error("can't find display") }
                output.reversed().withIndex().sumOf { (index, n) -> 10.0.pow(index) * n }
            }
            .sum()
            .toInt()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("year2021/day08/test")
    check(part1(testInput) == 26) { "result = ${part1(testInput)}" }
    check(part2(testInput) == 61229) { "result = ${part2(testInput)}" }

    val input = readInput("year2021/day08/input")
    println(part1(input))
    println(part2(input))
}
