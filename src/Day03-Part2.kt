fun main() {
    val input = readInput("Day03")

    val map = input.map {
        it.map {
            it == '1'

        }
    }
    val oxygenList = map.toMutableList()
    val co2ScrubberList = map.toMutableList()

    //for every bit
    for (i in 0..11){
        val mostCommonForPos = getMostCommonForPos(oxygenList, i)
        if(oxygenList.size > 1) {
            oxygenList.removeIf { it.get(i) == mostCommonForPos }
        }
    }

    for (i in 0..11){
        val mostCommonForPos = getMostCommonForPos(co2ScrubberList, i)
        if(co2ScrubberList.size > 1) {
            co2ScrubberList.removeIf { it.get(i) != mostCommonForPos }
        }
    }
    val oxygenString = getAsString(oxygenList.first())
    val co2String = getAsString(co2ScrubberList.first())
    println("Oxygen generator binary: ${oxygenString}")
    println("CO2 scrubber binary: ${co2String}")

    val oxygenGeneratorInt = oxygenString.toInt(2)
    val co2ScubberInt = co2String.toInt(2)
    println("Oxygen generator rating: ${oxygenGeneratorInt}")
    println("CO2 scrubber rating: ${co2ScubberInt}")

    println("Life suport rating: ${oxygenGeneratorInt*co2ScubberInt}")


}
fun getAsString(lista : List<Boolean>): String {
    return lista.joinToString("") { if(it) "1" else "0" }
}




