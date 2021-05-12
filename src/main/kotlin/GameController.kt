import javafx.animation.AnimationTimer
import javafx.beans.value.ObservableBooleanValue
import model.GameSpace
import tornadofx.Controller
import tornadofx.observable
import java.util.*

class GameController : Controller() {

    companion object{
        const val speedIncrement = 0.1
    }

    var modelA: GameSpace = GameSpace()
        private set

    var modelB: GameSpace = GameSpace()
        private set

    private val timer = GameTimer(listOf(modelA,modelB))

    fun startSimulation(){
        timer.start()
    }

    fun stopSimulation(){
        timer.stop()
    }

    fun speedUp(){
        timer.tick -= speedIncrement
    }

    fun speedDown(){
        timer.tick += speedIncrement
    }

}