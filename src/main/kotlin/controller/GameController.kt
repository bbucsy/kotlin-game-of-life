package controller

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.GameModel
import tornadofx.Controller
import java.io.File
import kotlin.random.Random

class GameController : Controller() {

    companion object {
        const val speedIncrement = 0.1
        const val gameSize = 50
    }

    val modelA: GameModel
    val modelB: GameModel

    private val timer: GameTimer

    init {
        val rnd = List(gameSize) {
            List(gameSize) { Random.nextBoolean() }
        }

        modelA = GameModel(gameSize, gameSize) { i, j -> rnd[i][j] }
        modelB = GameModel(gameSize, gameSize) { i, j -> rnd[i][j] }
        timer = GameTimer(listOf(modelA, modelB))
    }


    fun startSimulation() = timer.start()

    fun stopSimulation() = timer.stop()

    fun speedUp() {
        timer.tick -= speedIncrement
    }

    fun speedDown() {
        timer.tick += speedIncrement
    }

    fun save(file: File) = file.writeText(
        Json.encodeToString(listOf(modelA.save(), modelB.save()))
    )


    fun load(file: File) {
        val data = file.readText().let {
            Json.decodeFromString<List<List<Boolean>>>(it)
        }

        modelA.load(data[0])
        modelB.load(data[1])
    }

    fun clear() {
        stopSimulation()
        for (i in 0 until modelA.space.size) {
            modelA.space[i].value = false
        }
        for (i in 0 until modelB.space.size) {
            modelB.space[i].value = false
        }
    }

}