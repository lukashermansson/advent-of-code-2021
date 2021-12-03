fun main() {
    val input = readInput("Day03")

    val map = input.map {
        it.map {
            it == '1'

        }
    }
    var gammaString = ""
    for (i in 0..11) {
        gammaString+= if(getMostCommonForPos(map, i)) '1' else "0"
    }
    var epsilonString = ""
    for (i in 0..11) {
        epsilonString+= if(getMostCommonForPos(map, i)) '0' else "1"
    }

    println("Gamma string binary: ${gammaString}")
    println("Elipsion string binary: ${epsilonString}")

    val gamma = gammaString.toInt(2)
    val epsilon = epsilonString.toInt(2)
    println("Gamma value: ${gamma}")
    println("Epsilon value: ${epsilon}")

    println("Power of the submarine: ${ gamma*epsilon}")

}

fun getMostCommonForPos(listy : List<List<Boolean>>, pos : Int) : Boolean{
    var ones = 0
    var zeros = 0
    listy.forEach {
        if(it.get(pos)){
            ones++
        }else {
            zeros++
        }
    }
    return ones >= zeros
}
