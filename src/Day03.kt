import java.lang.Integer.parseInt

val grid = Grid()
fun main() {
    partOneDayThree()
}

fun partOneDayThree() {
    readInput("day3.input").lines().buildGrid()
    println(grid)
}

private fun List<String>.buildGrid(): List<Unit> {
    return mapIndexed { y, line ->
        line.forEachIndexed { x, c ->
            if(c != '.') {
                if(c.isDigit()) {
                    grid.numerals[Cell(x, y)] = c
                } else {
                    grid.symbols.add(Cell(x, y))
                }
            }
        }
    }
}

private fun getNumbersAroundSymbols() {

}

data class Grid(
    val numerals: HashMap<Cell, Char> = HashMap(),
    val symbols: MutableList<Cell> = mutableListOf()
)

data class Cell (
    val x: Int,
    val y: Int
)