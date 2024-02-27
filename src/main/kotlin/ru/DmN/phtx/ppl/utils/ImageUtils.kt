package ru.DmN.phtx.ppl.utils

import java.awt.Image
import java.io.File
import java.net.URL
import javax.imageio.ImageIO

object ImageUtils {
    fun read(module: String, url: String): Image =
        if (url.startsWith("http"))
            ImageIO.read(URL(url))
        else ImageIO.read(File("$module/$url"))
}