package year2021.day04

import readInput

typealias Board = List<Int>

private const val BoardWidth = 5
private val columnIndices =
    List(BoardWidth) { offset -> offset until BoardWidth * BoardWidth step BoardWidth }
private val rowIndices =
    List(BoardWidth) { offset -> (offset * BoardWidth) until (offset * BoardWidth + BoardWidth) }

fun Board(rawBoard: String): Board {
    return rawBoard
        .split("\n")
        .flatMap { row ->
            row
                .trim()
                .split("\\s+".toRegex())
                .map { cell -> cell.toInt() }
        }
}

fun Board.get(intProgression: IntProgression) = intProgression.map(::get)
fun Board.bingo(numbers: Set<Int>): Boolean {
    val columns = columnIndices.map(this::get)
    val rows = rowIndices.map(this::get)

    return (columns + rows)
        .map { it.toSet() }
        .any { it.intersect(numbers).size == BoardWidth }
}

fun main() {
    fun part1(input: List<String>): Int {
        val (numbersString) = input
        val numbers = numbersString.split(",").map { it.toInt() }
        val boards = input.drop(1).map(::Board)

        numbers.runningFold(emptySet<Int>()) { acc, n ->
            when (val board = boards.firstOrNull { it.bingo(acc + n) }) {
                null -> acc + n
                else -> return (board - acc - n).sum() * n
            }
        }

        error("no bingo found")
    }

    fun part2(input: List<String>): Int {
        val (numbersString) = input
        val numbers = numbersString.split(",").map { it.toInt() }
        val boards = input.drop(1).map(::Board).toMutableList()

        numbers.runningFold(emptySet<Int>()) { acc, n ->
            if (boards.size == 1 && boards.first().bingo(acc + n))
                return (boards.first() - acc - n).sum() * n
            else
                boards.removeAll { it.bingo(acc + n) }

            acc + n
        }

        error("no bingo found")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("year2021/day04/test", "\n\n")
    check(part1(testInput) == 4512) { "result = ${part1(testInput)}" }
    check(part2(testInput) == 1924) { "result = ${part2(testInput)}" }

    val input = readInput("year2021/day04/input", "\n\n")
    check(part1(input) == 72770)
    check(part2(input) == 13912)
    println(part1(input))
    println(part2(input))
}
