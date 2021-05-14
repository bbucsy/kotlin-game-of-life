package model

/**
 * Constant size matrix of a generic non null type
 * @property n The number of rows in the matrix
 * @property m The number of columns in the matrix
 */
class Matrix<T : Any>(val n: Int, val m: Int, init: (i: Int, j: Int) -> T) {

    private val matrix: List<List<T>> = List(n) { i ->
        List(m) { j ->
            init(i, j)
        }
    }

    /**
     * @return The number of cells stored in the matrix
     */
    val size: Int by lazy { n * m }

    /**
     * Access the matrix by two numbers
     * @param i The row of the cell
     * @param j The column of the cell
     */
    operator fun get(i: Int, j: Int): T = matrix[i][j]

    /**
     * Access the matrix by one index
     * @param k The index of the cell
     */
    operator fun get(k: Int) = matrix[k / m][k % m]


}