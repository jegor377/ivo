package com.company.controller

import com.company.model.validation.ImgExtensionValidator
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
    private val validator = ImgExtensionValidator(arrayOf(
            "png",
            "bmp",
            "jpg",
            "jpeg",
            "gif"
    ))

    fun getTitle(): String =
            if(app.parameters.raw.size == 1 && imageIsFile()) ("IVO 1.0 - [" + getDirEntry().path + "] MIT License Igor Santarek 2018") else "IVO 1.0 MIT License Igor Santarek 2018"

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

    private fun forwardImageId() {
        if(headingImageId+1 < dirEntries.size) {
            headingImageId++
        } else {
            headingImageId = 0
        }
    }

    private fun backwardImageId() {
        if(headingImageId > 0) {
            headingImageId--
        } else {
            headingImageId = dirEntries.size-1
        }
    }

    fun nextImage(imgView: ImageView) {
        forwardImageId()
        while(!validator.isValid(getDirEntry().extension)) forwardImageId()
        imgView.image = Image(imagePath)
    }

    fun prevImage(imgView: ImageView) {
        backwardImageId()
        while(!validator.isValid(getDirEntry().extension)) backwardImageId()
        imgView.image = Image(imagePath)
    }
}