import model.GameSpace
import tornadofx.Controller

class GameController: Controller() {
    val model: GameSpace = GameSpace()
}