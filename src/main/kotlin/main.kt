import model.BufferController
import model.BufferedCell
import model.GameSpace

fun main() {
    println("Hello Wolrd!")


    val game = GameSpace(3,3)

    game.space[0,1].value = true
    game.space[1,1].value = true
    game.space[1,0].value = true
    game.space[0,0].value = true

    println(game)

    repeat(3){
        game.update()
        println(game)
    }

    /*val bc = BufferController()

    val b1 = BufferedCell(bc)
    val b2 = BufferedCell(bc)



    b1.setDisplayValue(true)
    println("$b1\t$b2")
    b2.value = true
    println("$b1\t$b2")

    bc.swapBuffers()
    println("$b1\t$b2")
    b1.value = false
    b2.value = false
    println("$b1\t$b2")
    bc.swapBuffers()
    println("$b1\t$b2")
    */


}
