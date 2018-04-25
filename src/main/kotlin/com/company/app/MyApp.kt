package com.company.app

import com.company.view.MainView
import javafx.application.Application
import javafx.stage.Stage
import tornadofx.*
import java.util.*
import kotlin.reflect.KClass

class MyApp: App(MainView::class, Styles::class) {
    override fun start(stage: Stage) {
        super.start(stage)
    }
}