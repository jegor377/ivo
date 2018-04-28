package com.company.controller

import javafx.scene.image.Image
import javafx.scene.image.ImageView
import tornadofx.*
import java.io.File

class MainController: Controller() {
    private lateinit var dirEntries: Array<File>
    private var headingImageId: Int = 0
    val imagePath: String?
        get() {
            if(imageIsFile())
                return getDirEntry().toURI().toURL().toString()
            return null
        }

    fun getTitle(): String =
            if(app.parameters.raw.size == 1 && imageIsFile()) ("IVO - [" + getDirEntry().path + "] MIT License Igor Santarek 2017") else "IVO MIT License Igor Santarek 2017"

    private fun imageIsFile(): Boolean {
        if(app.parameters.raw.size == 1) {
            return File(app.parameters.raw[0]).isFile
        }
        return false
    }

    private fun getDirEntry(): File {
        if(!::dirEntries.isInitialized) {
            loadDirEntries()
            val file = File(app.parameters.raw[0])
            headingImageId = dirEntries.indexOfFirst { file.name == it.name }
        }
        return dirEntries[headingImageId]
    }

    private fun loadDirEntries() {
        val file = File(app.parameters.raw[0])
        if(file.isFile) {
            val fileDir = file.parentFile
            dirEntries = fileDir.listFiles()
        }
    }

    fun nextImage(imgView: ImageView) {
        if(headingImageId+1 < dirEntries.size) {
            headingImageId++
        } else {
            headingImageId = 0
        }
        imgView.image = Image(imagePath)
    }

    fun prevImage(imgView: ImageView) {
        if(headingImageId-1 > 0) {
            headingImageId--
        } else {
            headingImageId = dirEntries.size-1
        }
        imgView.image = Image(imagePath)
    }
}