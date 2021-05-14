package controller

import javafx.animation.AnimationTimer
import model.GameModel
import kotlin.properties.Delegates

/**
 * Class responsible for updating the given list of models.
 *
 * Updates each model in a given interval.
 * The update interval can be set from 0.1 seconds to 3 seconds
 */
class GameTimer(private val model: List<GameModel>) : AnimationTimer() {
    /**
     * The last time the handle function was called in nanoseconds.
     */
    private var lastTick: Long = 0

    /**
     * The elapsed time since the last update of the models.
     */
    private var elapsed: Double = 0.0

    /**
     * The time between to updates of the models.
     *
     * Can be set between 0.1 to 3 sec.
     */
    var updateInterval: Double by Delegates.vetoable(0.5) { _, _, newValue ->
        (newValue in 0.1..3.0)
    }

    /**
     * Starts the simulation
     */
    override fun start() {
        lastTick = System.nanoTime()
        super.start()
    }

    /**
     * Handles the tick of the timer.
     */
    override fun handle(now: Long) {
        // update elapsed time since the last update
        elapsed += (now - lastTick) / 1000000000.0

        if (elapsed > updateInterval) {
            elapsed = 0.0
            // Update each model
            model.forEach { it.update() }
        }

        lastTick = now
    }
}