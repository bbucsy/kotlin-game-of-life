package model


/**
 * Represents the state of the simulation space.
 */
class GameModel(val n: Int, val m: Int, initializer: (n: Int, m: Int) -> Boolean = { _, _ -> false }) {

    companion object {
        // relative positions of all the neighbours the cell can have
        val directions = listOf(
            -1 to -1, -1 to 0, -1 to 1,
            0 to -1, 0 to 1,
            1 to -1, 1 to 0, 1 to 1
        )
    }

    /**
     * The matrix which holds the cells.
     */
    val space: Matrix<BufferedCell> = Matrix(n, m) { n, m -> BufferedCell(initializer(n, m)) }

    /**
     * Steps the simulation based on the rules of the game
     */
    fun update() {
        for (i in 0 until n) {
            for (j in 0 until m) {
                //get cell state
                val alive = space[i, j].value

                //get number of alive neighbors
                var neighbors = 0
                for (dir in directions) {
                    val x = i + dir.first
                    val y = j + dir.second
                    if (x in 0 until n && y in 0 until m) {
                        if (space[x, y].value)
                            neighbors++
                    }

                }
                // set the next state based on the rules of the game
                // use buffer, so the neighbor cells can update properly
                space[i, j].buffer = when {
                    (alive && neighbors in 2..3) -> true
                    (!alive && neighbors == 3) -> true
                    else -> false
                }
            }
        }

        //swap buffer in each cell, to update them to next state
        for (i in 0 until space.size)
            space[i].swapBuffer()

    }

    /**
     * Load state from a list of boolean values
     *
     * @param l List of boolean values to load from
     * @throws IllegalArgumentException If the number of values in list isn't equal the number of cells the model has
     */
    fun load(l: List<Boolean>) {
        require(l.size == space.size) { "List must be equal to the number of cells per model" }

        for (i in 0 until space.size)
            space[i].value = l[i]
    }

    /**
     * @return A list of booleans representing the state of the model
     */
    fun toList(): List<Boolean> {
        return List(space.size) { i ->
            space[i].value
        }
    }
}