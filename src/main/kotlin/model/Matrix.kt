package model


class Matrix<T : Any>(val n: Int, val m: Int, init: (i: Int, j: Int) -> T) {

    private val matrix: List<List<T>> = List(n) { i ->
        List(m) { j ->
            init(i, j)
        }
    }

    val size: Int by lazy { n * m }
    operator fun get(i: Int, j: Int): T = matrix[i][j]
    operator fun get(k: Int) = matrix[k / m][k % m]


}