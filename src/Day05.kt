fun main() {
    val lineReader = LineReader()
    val input = readInput("Day05")

    val lines = input.map { lineReader.generatelineFromString(it) }

    val horizontalAndVerticalLines = lines.filter { it.isVerticalOrHorizontal() }

    horizontalAndVerticalLines.forEach {
        println(it)
    }
    val passedThrogh = mutableMapOf<Coordinate, Int>()

    horizontalAndVerticalLines.forEach {
        it.getPointsPassedThrogh().forEach {
            passedThrogh[it] = (passedThrogh[it] ?: 0) + 1
        }
    }
    println(passedThrogh)

    val dangerousAreas = passedThrogh.filter { it.value != 1 }
    println("Number of dangerous areas: ${dangerousAreas.size}" )
}

class LineReader {
    fun generatelineFromString(line: String) : Line{
        val (start, end) = line.split(" -> ")

        return Line(getCoordinateFromString(start), getCoordinateFromString(end))

    }
    private fun getCoordinateFromString(coord: String): Coordinate {
        val (x, y) = coord.split(",").map { it.toInt() }

        return Coordinate(x, y)
    }
}

class Line(val start: Coordinate, val end: Coordinate){
    fun isVerticalOrHorizontal() : Boolean {
        if(isVertical() || isHorizontal()) {
            return true
        }
        return false
    }
    private fun isVertical() : Boolean {
        return start.y == end.y
    }
    private fun isHorizontal() : Boolean {
        return start.x == end.x
    }

    override fun toString(): String {
        return "${start} -> ${end}"
    }

    fun getPointsPassedThrogh(): List<Coordinate> {
        if(isVertical()){
            if(start.x < end.x){
                return (start.x..end.x).map { Coordinate(it, start.y) }
            }else {
                return (start.x downTo end.x).map { Coordinate(it, start.y) }
            }
        }
        if(isHorizontal()){
            if(start.y < end.y) {
                return (start.y..end.y).map { Coordinate(start.x, it) }
            }else {
                return (start.y downTo end.y).map { Coordinate(start.x, it) }
            }
        }
        return listOf()
    }
}

data class Coordinate(val x: Int, val y: Int){

    override fun toString(): String {
        return "${x},${y}"
    }
}

