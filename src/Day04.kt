import kotlin.math.pow

fun main() {

    val digitRegex = """\d+""".toRegex()

    fun parseCards(cards: List<String>): List<Card> {
        val list = mutableListOf<Card>()
        cards.forEach { card ->
            card.split(":").apply {
                val c = Card()
                last().trim().split("|").apply {
                    c.addCards(digitRegex.findAll(first()).map { it.value.toInt() })
                    c.addCards(digitRegex.findAll(last()).map { it.value.toInt() })
                }
                list.add(c)
            }
        }
        return list
    }

    fun part1(cards: List<String>): Int {
        return parseCards(cards).sumOf { it.points() }
    }

    fun part2(cards: List<String>): Int {
        val multiplyFactors = IntArray(cards.size) { 0 }
        parseCards(cards).forEachIndexed { index, card ->
            multiplyFactors[index] = multiplyFactors[index].plus(1)
            repeat(multiplyFactors[index]) {
                multiplyFactors.incrementValues(index + 1, index + card.winnings(), 1)
            }
        }
        return multiplyFactors.sum()
    }


    val readInput = readInput("Day04")

    //**************************************//

    val points = part1(readInput)
    println(points)
    val cards = part2(readInput)
    println(cards)

}

private fun IntArray.incrementValues(startIndex: Int, endIndex: Int, value: Int) {
    if(startIndex > lastIndex) return
    if(endIndex >lastIndex) return
    (startIndex..endIndex).forEach {
        set(it, get(it).plus(value))
    }
}

class Card {
    private val winningsAndScratched = mutableMapOf<Int, Int>()

    fun winnings() = winningsAndScratched.values.sum()
    fun points(): Int = 2.0.pow(winningsAndScratched.values.sum() - 1).toInt()
    fun addCards(list: Sequence<Int>) {
        list.forEach {
            winningsAndScratched[it] = (winningsAndScratched[it]?.plus(1)) ?: 0
        }
    }
}