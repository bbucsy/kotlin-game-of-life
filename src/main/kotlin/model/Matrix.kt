package model


class Matrix<T : Any>(private val n: Int, private val m: Int, init: (i: Int, j: Int) -> T) {

    private val matrix: List<List<T>> = List(n) { i ->
        List(m) { j ->
            init(i, j)
        }
    };

    operator fun get(i: Int, j: Int): T = matrix[i][j]

    override fun toString(): String {
        var str = ""
        for (i in 0 until n) {
            for (j in 0 until m) {
                str += matrix[i][j].toString()
            }
            str += "\n"
        }
        return str
    }

}