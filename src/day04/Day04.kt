package day04

import readInput

typealias Cell = Pair<Int, Boolean>

data class Board(private val state: MutableList<Cell>) {
    val won: Boolean
        get() = fullRows().isNotEmpty() || hasFullColumn()

    val unmarkedCells: List<Cell>
        get() = state.filter { (_, marked) -> !marked }

    private fun fullRows() =
        state
            .windowed(5, step = 5)
            .filter { rows -> rows.all { (_, played) -> played } }

    private fun hasFullColumn(): Boolean {
        val columns = (0 until 5)
            .map { offset -> offset until state.size step 5 }
            .map { it.map { state[it] } }
            .filter { columns -> columns.all { (_, played) -> played } }

        return columns.isNotEmpty()
    }

    fun play(number: Int) {
        val index = state.indexOfFirst { (n, _) -> n == number }
        if (index != -1)
            state[index] = number to true
    }
}

fun main() {

    fun assembleBoards(input: List<String>): List<Board> {
        return input
            .filter { it.contains("\n") }
            .map { board ->
                board.split("\n")
                    .map { row ->
                        row.trim().split("\\s+".toRegex())
                            .map { cell -> cell.toInt() }
                    }
                    .flatten()
            }
            .map { board ->
                board.map { it to false }.toMutableList()
            }
            .map(::Board)
    }

    fun part1(input: List<String>): Int {
        val (numbersString) = input
        val numbers = numbersString.split(",").map { it.toInt() }
        val boards = assembleBoards(input)

        for (num in numbers) {
            for (board in boards) {
                board.play(num)
                if (board.won)
                    return board.unmarkedCells.sumOf { (n, _) -> n } * num
            }
        }

        return input.size
    }

    fun part2(input: List<String>): Int {
        val (numbersString) = input
        val numbers = numbersString.split(",").map { it.toInt() }
        val boards = assembleBoards(input)

        for (num in numbers) {
            for (board in boards) {
                board.play(num)
                if (boards.count { it.won } == boards.size) {
                    return board.unmarkedCells.sumOf { (n, _) -> n } * num
                }
            }
        }

        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day04/test", "\n\n")
    check(part1(testInput) == 4512) { "result = ${part1(testInput)}" }
    check(part2(testInput) == 1924) { "result = ${part2(testInput)}" }

    val input = readInput("day04/input", "\n\n")
    check(part1(input) == 72770)
    check(part2(input) == 13912)
    println(part1(input))
    println(part2(input))
}
