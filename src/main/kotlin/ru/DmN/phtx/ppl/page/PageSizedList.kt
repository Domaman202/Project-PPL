package ru.DmN.phtx.ppl.page

import ru.DmN.phtx.ppl.element.Element
import java.awt.AlphaComposite
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_ARGB

class PageSizedList(val width: Int, val height: Int, list: MutableList<Element>) : PageList(list) {
    constructor(width: Int, height: Int) : this(width, height, mutableListOf())

    override fun paint(w: Dimension, g: Graphics2D) {
        //
        val image = BufferedImage(width, height, TYPE_INT_ARGB)
        val graphics = image.graphics as Graphics2D
        graphics.composite = AlphaComposite.Clear
        graphics.fillRect(0, 0, width, height)
        super.paint(Dimension(width, height), graphics)
        //
//        // Отладочная сетка
//        graphics.color = Color.RED
//        graphics.drawLine(0, 0, width, height)
//        graphics.drawLine(width, 0, 0, height)
//        graphics.drawLine(width / 2, 0, width / 2, height)
//        graphics.drawLine(0, height / 2, width, height / 2)
//        // Отладочная сетка
        //
        g.drawImage(image, 0, 0, w.width, w.height, null)
    }
}