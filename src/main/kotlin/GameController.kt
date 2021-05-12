import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.GameModel
import tornadofx.Controller
import java.io.File

class GameController : Controller() {

    companion object{
        const val speedIncrement = 0.1
    }

    var modelA: GameModel = GameModel()
        private set

    var modelB: GameModel = GameModel()
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

    fun save(path: String){
        try {
            File(path).writeText(
                Json.encodeToString(listOf<List<Boolean>>(modelA.save(),modelB.save()))
            )
        }
        catch (e: Exception){
            println("Could not save file")
            e.printStackTrace()
        }
    }

    fun load(path: String){
        try {
            val data = File(path).readText().let {
                Json.decodeFromString<List<List<Boolean>>>(it)
            }

            modelA.load(data[0])
            modelB.load(data[1])


        }catch (e:Exception){
            println("Could not open file")
            e.printStackTrace()
        }
    }

}