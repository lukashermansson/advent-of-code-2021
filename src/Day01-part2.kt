fun main() {
    val input = readInput("Day01")

    val valuesAsInts = input.map { it.toInt() }

    var numberOfTimesIncreased = 0
    var previusNumber = getSlidingSum(valuesAsInts, 0)

    val summedValues = mutableListOf<Int>()
    for (i in valuesAsInts.indices step 1) {
        try {
            summedValues.add(getSlidingSum(valuesAsInts, i))
        } catch (e : RuntimeException){
            //Out of summs
        }
    }
    summedValues.drop(1).forEach {
        if(it > previusNumber) {
            numberOfTimesIncreased++
        }
        previusNumber = it
    }

    println("Numbers larger: ${numberOfTimesIncreased}")
}

fun getSlidingSum(list: List<Int>, index : Int) : Int{
    return list[index] + list[index + 1] + list[index + 2]
}

