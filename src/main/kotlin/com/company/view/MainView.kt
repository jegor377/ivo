package com.company.view

import com.company.controller.MainController
import javafx.scene.paint.Paint
import tornadofx.*

class MainView : View() {
    private val controller: MainController by inject()

    override val root = hbox {
        if(controller.imagePath != null) imageview(controller.imagePath)
        style {
            backgroundColor += Paint.valueOf("444")
        }
    }

    init {
        title = controller.title
    }
}