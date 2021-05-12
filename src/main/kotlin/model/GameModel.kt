package model

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException


class GameModel(private val n: Int = 50, private val m: Int = 50, initializer: (n: Int, m: Int) -> Boolean = {_,_ -> false}) {

    companion object {
        val directions = listOf(
            -1 to -1, -1 to 0, -1 to 1,
            0 to -1, 0 to 1,
            1 to -1, 1 to 0, 1 to 1
        )
    }

    val space: Matrix<BufferedCell> = Matrix(n, m) { n, m -> BufferedCell(initializer(n,m)) }

    fun update() {
        for (i in 0 until n) {
            for (j in 0 until m) {
                val alive = space[i, j].value
                var neighbors = 0
                for (dir in directions) {
                    val x = i + dir.first
                    val y = j + dir.second
                    if (x in 0 until n && y in 0 until m) {
                        if (space[x, y].value)
                            neighbors++
                    }

                }
                space[i, j].buffer = when {
                    (alive && neighbors in 2..3) -> true
                    (!alive && neighbors == 3) -> true
                    else -> false
                }
            }
        }

        for (i in 0 until space.size)
            space[i].swapBuffer()

    }

    fun load(l:List<Boolean> ) {
        if (l.size != space.size)
            throw Exception("Wrong number of data to load")

        for(i in 0 until space.size)
            space[i].value = l[i]
    }

    fun save() : List<Boolean>{
        return List(space.size){
            i -> space[i].value
        }
    }
}