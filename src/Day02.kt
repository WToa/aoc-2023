import java.util.*

const val RED_CUBE_LIMIT = 12
const val GREEN_CUBE_LIMIT = 13
const val BLUE_CUBE_LIMIT = 14
fun main() {
    val input = readInput("day2.input").lines()
    val resultPartOne = input.dayTwoPartOneSolver()
    println("Part One")
    println(resultPartOne)

    val resultPartTwo = input.dayTwoPartTwoSolver()
    println("Part Two")
    println(resultPartTwo)
}

private fun List<String>.dayTwoPartOneSolver(): Int {
    return map { line ->
        getGamePartOne(line, RED_CUBE_LIMIT, GREEN_CUBE_LIMIT, BLUE_CUBE_LIMIT)
    }.sumOf { it.id }
}

private fun List<String>.dayTwoPartTwoSolver(): Int {
    return sumOf { line ->
        getGamePartTwo(line).cubes.first().map { it.count }.reduce { acc, i -> acc * i }
    }
}

private fun getGamePartOne(line: String, redCubeLimit: Int, greenCubeLimit: Int, blueCubeLimit: Int): Game {
    var redTotal = 0
    var greenTotal = 0
    var blueTotal = 0

    val parsedLine = line.split(":")
    val gameId = parsedLine[0].filter { it.isDigit() }.toInt()
    val cubeSets = parsedLine[1].split(";").map(::getCubeSet)

    // validate cubes
    cubeSets.forEach {
        it.forEach {
            when(it.color) {
                Color.RED -> {
                    if(it.count > redCubeLimit) {
                        return@getGamePartOne Game(0, listOf(emptyList())) // hack
                    }
                }
                Color.GREEN -> {
                    if(it.count > greenCubeLimit) {
                        return@getGamePartOne Game(0, listOf(emptyList())) // hack
                    }
                }
                Color.BLUE -> {
                    if(it.count > blueCubeLimit) {
                        return@getGamePartOne Game(0, listOf(emptyList())) // hack
                    }
                }
            }
        }
        if(redTotal > redCubeLimit || greenTotal > greenCubeLimit || blueTotal > blueCubeLimit) {
            return@getGamePartOne Game(0, listOf(emptyList())) // hack
        }
    }

    return Game(gameId, cubeSets)
}

private fun getGamePartTwo(line: String): Game {
    val parsedLine = line.split(":")
    val gameId = parsedLine[0].filter { it.isDigit() }.toInt()
    val cubeSets = parsedLine[1].split(";").map(::getCubeSet).flatten()

    println(cubeSets)
    val largestNumberOfRedCubes = cubeSets.filter { it.color == Color.RED }.maxBy { it.count }
    val largestNumberOfGreenCubes = cubeSets.filter { it.color == Color.GREEN }.maxBy { it.count }
    val largestNumberOfBlueCubes = cubeSets.filter { it.color == Color.BLUE }.maxBy { it.count }

    return Game(gameId, listOf(listOf(largestNumberOfRedCubes, largestNumberOfGreenCubes, largestNumberOfBlueCubes)))
}

private fun getCubeSet(cubeSet: String): List<Cube> {
    val cubes = cubeSet.split(",").map { it.trim() }
    return cubes.map {
        Cube(
            color = Color.valueOf(it.filter { !it.isDigit() }.trim().uppercase(Locale.getDefault())),
            count = it.filter { it.isDigit() }.toInt()
        )
    }
}


data class Game(
    val id: Int,
    val cubes: List<List<Cube>>
)

data class Cube(
    val color: Color,
    val count: Int
)

enum class Color {
    RED, BLUE, GREEN
}