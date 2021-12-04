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

    val boardsThatHasWon = mutableListOf<Int>()

    var latestBoardToWinFinalScore = 0

    calledNumbers.forEach { number ->
        boards.forEachIndexed { index, board ->
            board.callNumber(number)
            if(board.hasWon()){
                if(!boardsThatHasWon.contains(index)) {
                    boardsThatHasWon.add(index)

                    val sum = board.sumOfAllUnMarkedNumbers()
                    latestBoardToWinFinalScore = sum * number

                    board.printBoard()
                    println()
                }



            }
        }
    }

    println("Score of latest board to win: ${latestBoardToWinFinalScore}")

}
