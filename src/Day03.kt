fun main() {

    val numberRegex = """\d+""".toRegex()
    val specialRegex = """[^0-9.]""".toRegex()

    fun part1(engine: List<String>) {

        val numbersGrid:MutableList<MutableList<MatchResult>> = mutableListOf()
        val symbolGrid:MutableList<MutableList<MatchResult>> = mutableListOf()

        engine.forEachIndexed {index, line ->
            val numbers = numberRegex.findAll(line)
            var list = mutableListOf<MatchResult>()
            numbers.forEach { result ->
                list.add(result)
            }
            numbersGrid.add(index, list)
            val specials = specialRegex.findAll(line)
            list = mutableListOf()
            specials.forEach { result ->
                list.add(result)
            }
            symbolGrid.add(index,list)
        }

        printGrids(numbersGrid, symbolGrid)

    }

    val readInput = readInput("Day03")
    part1(engine = readInput)
}

private fun printGrids(numbers:List<List<MatchResult>>, symbols:List<List<MatchResult>>){
    for (l in 0..numbers.lastIndex){
        numbers[l].forEach {
            print("${it.range}".plus(" "))
        }
        println()
        symbols[l].forEach {
            print("${it.range}".plus(" "))
        }
        println()
    }
}