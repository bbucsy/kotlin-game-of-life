package controller

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.GameModel
import tornadofx.Controller
import java.io.File
import kotlin.random.Random

/**
 * Controller for the game application.
 *
 * Stores and initializes the two separate game models, and the timer
 * which updates the models in given time intervals.
 * Controls the state of the simulation, and the models.
 */
class GameController : Controller() {

    companion object {
        /**
         * The speed of the change in simulation speed in seconds.
         */
        const val speedIncrement = 0.1

        /**
         * Size of the game space. Sets the width and height of the matrix.
         */
        const val gameSize = 50
    }

    /**
     * The model shown on the right side of the view.
     */
    val modelA: GameModel

    /**
     * The model shown on the left side of the view.
     */
    val modelB: GameModel

    /**
     * Timer controlling the simulation.
     */
    private val timer: GameTimer

    init {
        // Create a matrix with random values
        val rnd = List(gameSize) {
            List(gameSize) { Random.nextBoolean() }
        }

        // set each model to the exact same initial state
        modelA = GameModel(gameSize, gameSize) { i, j -> rnd[i][j] }
        modelB = GameModel(gameSize, gameSize) { i, j -> rnd[i][j] }

        // create the timer, and give it the list of updatable models
        timer = GameTimer(listOf(modelA, modelB))
    }


    /**
     * Starts the simulation.
     */
    fun startSimulation() = timer.start()

    /**
     * Pauses the simulation.
     */
    fun stopSimulation() = timer.stop()

    /**
     * Speeds up the simulation by some constant value.
     *
     * If the new value is out of the boundary specified by the timer,
     * then the function has no effect.
     */
    fun speedUp() {
        timer.updateInterval -= speedIncrement
    }

    /**
     * Slows down the simulation by some constant value.
     *
     * If the new value is out of the boundary specified by the timer,
     * then the function has no effect.
     */
    fun speedDown() {
        timer.updateInterval += speedIncrement
    }

    /**
     * Saves the state of the models to the specified file.
     *
     * @param file The file to write the state.
     */
    fun save(file: File) = file.writeText(
        Json.encodeToString(listOf(modelA.toList(), modelB.toList()))
    )

    /**
     * Loads the saved state of the models from a file.
     *
     * @param file The file to read the states from.
     */
    fun load(file: File) {
        val data = file.readText().let {
            Json.decodeFromString<List<List<Boolean>>>(it)
        }

        modelA.load(data[0])
        modelB.load(data[1])
    }

    /**
     * Clears both models, and sets each cell to "Dead" state.
     */
    fun clear() {
        stopSimulation()
        for (i in 0 until modelA.space.size) {
            modelA.space[i].value = false
        }
        for (i in 0 until modelB.space.size) {
            modelB.space[i].value = false
        }
    }

    /**
     * Sets the two models to exactly the same random state
     */
    fun random(){
        stopSimulation()
        // create random state
        val rnd = List(gameSize* gameSize){
            Random.nextBoolean()
        }
        // load models with rand values
        modelA.load(rnd)
        modelB.load(rnd)
    }

}