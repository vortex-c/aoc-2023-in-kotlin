fun main() {

    val readInput = readInput("Day07")

    fun part1(cards: List<String>): Long = cards.map {
        val (card, bid) = it.split("\\s+".toRegex())
        CamelCard(card.trim()) to bid.trim().toLong()
    }.let {
        it.sortedBy { hand -> hand.first }
    }.mapIndexed { i, hand ->
        hand.second * i.plus(1)
    }.sum()

    val p1 =  part1(readInput)
    println(p1)
}

private data class CamelCard(
    val card: String
) : Comparable<CamelCard> {

    companion object {
        private val strength = mapOf(
            'A' to 16,
            'K' to 15,
            'Q' to 14,
            'J' to 13,
            'T' to 12,
            '9' to 9,
            '8' to 8,
            '7' to 7,
            '6' to 6,
            '5' to 5,
            '4' to 4,
            '3' to 3,
            '2' to 2
        )
    }

    val type by lazy {
        val t = card.groupingBy { it }.eachCount()
        when {
            t.values.size == 1 -> 7
            t.values.size == 5 -> 1
            t.values.size == 4 -> 2
            t.values.size == 3 && t.values.contains(3) -> 4
            t.values.size == 3 && t.values.contains(2) -> 3
            t.values.size == 2 && t.values.contains(4) -> 6
            else -> 5
        }
    }

    override fun compareTo(other: CamelCard): Int {
        return when {
            this.type > other.type -> this.type - other.type
            this.type < other.type -> this.type - other.type
            else -> {
                this.card.zip(other.card).forEach {
                    if (it.first != it.second) {
                        return strength[it.first]!! - strength[it.second]!!
                    }
                }
                return 0
            }
        }
    }
}