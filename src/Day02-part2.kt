fun main() {
    val input = readInput("Day02")

    val values = input.map {
        val list = it.split(" ")
        Pair(list.get(0), list.get(1).toInt())
    }

    var depth = 0
    var horizontal = 0
    var aim = 0

    values.forEach {
        if(it.first == "forward"){
            horizontal+= it.second
            depth+= it.second*aim
        }
        if(it.first == "down"){
            aim+= it.second
        }
        if(it.first == "up"){
            aim-= it.second
        }
    }
    println("final horizontal postition: ${horizontal}")
    println("Final depth: ${depth}")
    println("Multiplied: ${horizontal * depth}")
}

