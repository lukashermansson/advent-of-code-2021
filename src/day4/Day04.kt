package day4

import readInput

fun main() {
    val boardReader  = BoardReader()
    val input = readInput("day4/Day04")

    val calledNumbers = input[0].split(",").map { it.toInt() }

    val boards: MutableList<BingoBoard> = mutableListOf()

    val boardsValues = input.drop(2)

    boardsValues.filterIndexed { index, value -> index % 6 == 0 }.forEachIndexed {
        index, value ->
        val startValue = index*6
        boards.add(boardReader.readBoard(listOf(boardsValues[startValue],
        boardsValues[startValue + 1],
        boardsValues[startValue + 2],
        boardsValues[startValue + 3],
        boardsValues[startValue + 4])))
    }

    calledNumbers.forEach { number ->
        boards.forEach {
            it.callNumber(number)
            if(it.hasWon()){
                //winning board
                it.printBoard()
                println(number)

                val sum = it.sumOfAllUnMarkedNumbers()

                println("Sum of unmarkedNumbers: ${sum}")

                println("Final score: ${sum * number}")

                return
            }
        }
    }


}
class BoardReader() {
    fun readBoard(rows: List<String>) : BingoBoard {
        val map = rows.map {
            it.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
        }
        return BingoBoard(map.map { it.map { Number(it, false) } })
    }
}

class Number(val value: Int, var marked: Boolean){
    fun mark() {
        marked = true
    }
}
class BingoBoard(val state: List<List<Number>>) {

    fun callNumber(number: Int){
        state.forEach {
            it.forEach {
                if(it.value == number){
                    it.mark()
                }
            }
        }
    }
    fun sumOfAllUnMarkedNumbers() : Int{
        var sum = 0
        val map = state.flatMap { it.filter { !it.marked }.map { it.value } }

        map.forEach { sum+= it }

        return sum
    }

    fun hasWon() : Boolean {
        var hasWon = false
        //rows
        state.forEach {
            hasWon = isAllMarked(it)
        }
        for (rows in 0..4){
            if(!hasWon) {
                hasWon = isAllMarked(
                    listOf(
                        state.get(0).get(rows),
                        state.get(1).get(rows),
                        state.get(2).get(rows),
                        state.get(3).get(rows),
                        state.get(4).get(rows)
                    )
                )
            }

        }
        return hasWon

    }

    private fun isAllMarked(list: List<Number>): Boolean {
        return list.filter { !it.marked }.isEmpty()
    }

    fun printBoard() {
        state.forEach {
            println(it.map {
                if(it.marked) " (${it.value}) " else " ${it.value} "
            })

        }
    }
}
