package year2021.day05

import readInput

typealias Heatmap = MutableMap<Point, Int>

fun Point(list: List<Int>) = Point(list[0], list[1])
data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
}

data class Line(val start: Point, val end: Point) : Iterable<Point> {
    val isHorizontal = start.x == end.x
    val isVertical = start.y == end.y

    override fun iterator(): Iterator<Point> {
        val slope = when {
            start.x == end.x && start.y < end.y -> Point(0, 1)
            start.x == end.x && start.y > end.y -> Point(0, -1)
            start.y == end.y && start.x < end.x -> Point(1, 0)
            start.y == end.y && start.x > end.x -> Point(-1, 0)
            start.x < end.x && start.y < end.y -> Point(1, 1)
            start.x > end.x && start.y > end.y -> Point(-1, -1)
            start.x > end.x && start.y < end.y -> Point(-1, 1)
            start.x < end.x && start.y > end.y -> Point(1, -1)
            else -> error("Could not determine slope $this")
        }
        var current = start
        val points = mutableListOf<Point>()
        do {
            points.add(current)
            current += slope
        } while (current != end)
        points += end
        return points.iterator()
    }
}

fun main() {
    fun createHeatmap(lines: List<Line>): Heatmap {
        val heatMap = mutableMapOf<Point, Int>()
        for (line in lines) {
            for (point in line) {
                heatMap[point] = heatMap.getOrDefault(point, 0) + 1
            }
        }
        return heatMap
    }

    fun mapLines(input: List<String>) = input.map {
        val (fromString, toString) = it.split(" -> ")

        val from = fromString.split(",").map { it.toInt() }
        val to = toString.split(",").map { it.toInt() }

        Line(Point(from), Point(to))
    }

    fun part1(input: List<String>): Int {
        val lines = mapLines(input)
            .filter { it.isHorizontal || it.isVertical }

        return createHeatmap(lines).count { (_, value) -> value >= 2 }
    }

    fun part2(input: List<String>): Int {
        val lines = mapLines(input)
        return createHeatmap(lines).count { (_, value) -> value >= 2 }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("year2021/day05/test")
    check(part1(testInput) == 5) { "result = ${part1(testInput)}" }
    check(part2(testInput) == 12) { "result = ${part2(testInput)}" }

    val input = readInput("year2021/day05/input")
    println(part1(input))
    println(part2(input))
}
