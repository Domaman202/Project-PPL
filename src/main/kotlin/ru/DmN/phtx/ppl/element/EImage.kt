package ru.DmN.phtx.ppl.element

import ru.DmN.phtx.ppl.element.Element.SizeType.DYNAMIC
import java.awt.Dimension
import java.awt.Graphics2D
import java.awt.Image
import java.awt.RenderingHints.KEY_ANTIALIASING
import java.awt.RenderingHints.VALUE_ANTIALIAS_ON
import java.awt.geom.RoundRectangle2D
import java.net.URL
import javax.imageio.ImageIO
import kotlin.math.max

class EImage(private val image: Image) : Element() {
    constructor(url: URL) : this(ImageIO.read(url))

    override val type: SizeType
        get() = DYNAMIC

    override fun size(w: Dimension, g: Graphics2D): Size =
        Size(image.getWidth(null), image.getHeight(null) + 32)

    override fun paint(o: Offset, f: Size, w: Dimension, g: Graphics2D) {
        g.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON)
        val x = max(o.left, 32)
        val y = o.up + 32
        val sizeX = f.width - 64
        val sizeY = if (w.height == f.height || o.up > 0) f.height - 64 else f.height
        g.clip = g.clip.apply {
            g.clip = RoundRectangle2D.Float(x.toFloat(), y.toFloat(), sizeX.toFloat(), sizeY.toFloat(), 50f, 50f)
            g.drawImage(image, x, y, sizeX, sizeY, null)
        }
    }
}