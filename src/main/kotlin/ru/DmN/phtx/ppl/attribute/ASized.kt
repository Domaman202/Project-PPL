package ru.DmN.phtx.ppl.attribute

import ru.DmN.phtx.ppl.element.Element
import java.awt.Dimension
import java.awt.Graphics2D

class ASized(private val sizeX: Int, private val sizeY: Int, private val element: Element) : Element() {
    override val type: SizeType
        get() = element.type

    override fun size(w: Dimension, g: Graphics2D): Size =
        element.size(w, g).run { Size(width, height) }

    override fun paint(o: Offset, f: Size, w: Dimension, g: Graphics2D) {
        element.paint(
            o,
            if (sizeX > -1)
                if (sizeY > -1)
                    Size(sizeX, sizeY)
                else Size(sizeX, f.height)
            else if (sizeY > -1)
                Size(f.width, sizeY)
            else Size(f.width, f.height),
            w,
            g
        )
    }
}