
fun main(){

    val readInput = readInput("Day06")

    fun part1(race:List<String>):Int{
        return race.map {r ->
          r.split(":").last().trim().split("\\s+".toRegex()).map { it.trim().toInt() }
        }.let {
            it.first().zip(it.last())
        }.map { (time, distance) ->
            (0..time).filter {
               it*(time-it) > distance
            }.size
        }.reduce{acc, i ->
            i*acc
        }
    }


    //This is not optimized, we can optimize to run in ~= O(logn) time using binary search
    fun part2(race:List<String>):Int{
        return race.map {r ->
            r.split(":").last().trim().split("\\s+".toRegex()).map { it.trim().toInt() }
        }.let {
            it.first().joinToString(separator = "").toLong() to it.last().joinToString(separator = "").toLong()
        }.let {(time, distance) ->
            (0..time).filter {
                it*(time-it) > distance
            }.size
        }
    }

    val p1 =  part1(readInput)
    println(p1)
    val p2 = part2(readInput)
    println(p2)
}