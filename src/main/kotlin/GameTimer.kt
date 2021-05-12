import javafx.animation.AnimationTimer
import model.GameSpace
import kotlin.properties.Delegates

class GameTimer(val model: List<GameSpace>) : AnimationTimer() {
    private var lastUpdate: Long = 0
    private var ellapsed: Double = 0.0

    var tick: Double by Delegates.vetoable(0.5) { _, _, newValue ->
        println("$newValue")
        (newValue in 0.1..3.0)
    }

    override fun start() {
        lastUpdate = System.nanoTime()
        super.start()
    }

    override fun handle(now: Long) {
        val elapsedNanoSeconds = now - lastUpdate

        val elapsedSeconds = elapsedNanoSeconds / 1000000000.0
        ellapsed += elapsedSeconds
        if (ellapsed > tick) {
            ellapsed = 0.0
            model.forEach { it.update() }
        }

        lastUpdate = now
    }
}