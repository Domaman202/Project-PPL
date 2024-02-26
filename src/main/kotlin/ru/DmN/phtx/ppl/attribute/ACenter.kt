package ru.DmN.phtx.ppl.attribute

import ru.DmN.phtx.ppl.element.Element
import java.awt.Dimension
import java.awt.Graphics2D

class ACenter(private val element: Element) : Element() {
    override val type: SizeType
        get() = element.type

    override fun size(w: Dimension, g: Graphics2D): Size =
        element.size(w, g)

    override fun paint(o: Offset, f: Size, w: Dimension, g: Graphics2D) {
        val size = size(w, g)
        val width = (w.width - o.right + o.left) / 2 - size.width / 2
        element.paint(Offset(o.up,o.down, width, width), f, w, g)
    }
}