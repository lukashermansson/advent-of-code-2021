fun main() {
    val input = readInput("Day01")

    val valuesAsInts = input.map { it.toInt() }

    var numberOfTimesIncreased = 0
    var previusNumber = valuesAsInts.first()

    valuesAsInts.drop(1).forEach {
        if(it > previusNumber) {
            numberOfTimesIncreased++
        }
        previusNumber = it
    }

    println("Numbers larger: ${numberOfTimesIncreased}")
}
