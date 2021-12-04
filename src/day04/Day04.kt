package day04

import readInput

typealias Cell = Pair<Int, Boolean>

private const val BoardWidth = 5

fun Board(rawBoard: String): Board {
    val cells: List<Cell> = rawBoard
        .split("\n")
        .flatMap { row ->
            row
                .trim()
                .split("\\s+".toRegex())
                .map { cell -> cell.toInt() to false }
        }
    return Board(cells)
}

class Board(cells: List<Cell>) {
    private val state = cells.toMutableList()

    companion object {
        private val columnIndices =
            List(BoardWidth) { offset -> offset until BoardWidth * BoardWidth step BoardWidth }
        private val rowIndices =
            List(BoardWidth) { offset -> (offset * BoardWidth) until (offset * BoardWidth + BoardWidth) }

        private fun onlyMarked(cells: List<Cell>): Boolean = cells.all { (_, marked) -> marked }
    }

    val won: Boolean
        get() = fullRows().isNotEmpty() || fullColumns().isNotEmpty()

    val unmarkedCells: List<Cell>
        get() = state.filter { (_, marked) -> !marked }

    private fun fullRows() =
        rowIndices
            .map { row -> row.map { state[it] } }
            .filter(Board::onlyMarked)

    private fun fullColumns() =
        columnIndices
            .map { column -> column.map { state[it] } }
            .filter(Board::onlyMarked)

    fun play(number: Int) {
        val index = state.indexOfFirst { (n, _) -> n == number }
        if (index != -1)
            state[index] = number to true
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val (numbersString) = input
        val numbers = numbersString.split(",").map { it.toInt() }
        val boards = input.drop(1).map(::Board)

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
        val boards = input.drop(1).map(::Board)

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
