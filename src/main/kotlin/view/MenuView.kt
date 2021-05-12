package view

import javafx.scene.Parent
import javafx.scene.paint.Color
import tornadofx.*

val numbers = (1..10).toMutableList()

class MenuView : View() {
    override val root = datagrid(numbers) {
        cellHeight = 75.0
        cellWidth = 75.0

        multiSelect = true

        cellCache {
            stackpane {
                circle(radius = 25.0) {
                    fill = Color.FORESTGREEN
                }
                label(it.toString())
                setOnMouseClicked {
                    println("clicked")
                    numbers[0]++
                }
            }
        }
    }

}