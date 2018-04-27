package com.company.app

import com.company.view.MainView
import javafx.stage.Stage
import tornadofx.*

class MyApp: App(MainView::class, Styles::class) {
    override fun start(stage: Stage) {
        stage.minWidth = 1000.0
        stage.minHeight = 600.0
        stage.isMaximized = true
        super.start(stage)
    }
}