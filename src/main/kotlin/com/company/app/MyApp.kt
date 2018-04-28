package com.company.app

import com.company.view.MainView
import javafx.stage.Stage
import tornadofx.*

class MyApp: App(MainView::class, Styles::class) {
    override fun start(stage: Stage) {
        stage.minWidth = 800.0
        stage.minHeight = 500.0
        stage.isMaximized = true
        super.start(stage)
    }
}