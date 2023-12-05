fun main() {
    partOne()
    partTwo()
}

fun partOne() {
    val result = readInput("day1.input").lines().dayOneSolver()
    println(result)
}

fun partTwo() {
    val result = readInput("day1.input").lines().map(::getFirstAndLastNumber).dayOneSolver()
    println(result)
}

private fun List<String>.dayOneSolver(): Int {
    return map { line ->
        val digits: String = line.filter { it.isDigit() }
        return@map "${digits.first()}${digits.last()}".toInt()
    }.reduce { acc, nextDigit -> acc + nextDigit}
}

private fun getFirstAndLastNumber(line: String): String {

    val firstNumber = numberMap.keys.map { numberMap[it]!! to line.indexOf(it) }
        .filter { it.second != -1 }
        .minBy { it.second }.first
    val lastNumber = numberMap.keys.map { numberMap[it]!! to line.lastIndexOf(it) }
        .filter { it.second != -1 }
        .maxBy { it.second }.first

    return "$firstNumber$lastNumber"
}

private val numberMap = (1..9).associateBy { it.toString() } + mutableMapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)
