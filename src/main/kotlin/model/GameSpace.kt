package model


class GameSpace(private val n:Int = 200, private val m:Int = 200) {

    companion object{
        val directions = listOf(
            -1 to -1, -1 to 0, -1 to 1,
            0 to -1, 0 to 1,
            1 to -1, 1 to 0, 1 to 1 )
    }

    private val bufferController = BufferController()
    val space : Matrix<BufferedCell> = Matrix(n,m) { _, _ -> BufferedCell(bufferController) }

    fun update(){
        for (i in 0 until n){
            for (j in 0 until m){
                val alive  = space[i,j].value
                var neighbors = 0
                for (dir in directions){
                    val x = i + dir.first
                    val y = j + dir.second
                    if(x in 0 until n && y in 0 until m){
                        if(space[x,y].value)
                            neighbors++
                    }

                }
                space[i,j].nextValue = when{
                    (alive && neighbors in 2..3) -> true
                    (!alive && neighbors == 3) -> true
                    else -> false
                }
            }
        }


        bufferController.swapBuffers();
        //TODO: Call view update method

    }

    override fun toString(): String {
        return "-".repeat(m) + "\n$space" + "-".repeat(m)
    }

}