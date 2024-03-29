package ru.DmN.phtx.ppl.element

import ru.DmN.phtx.ppl.element.Element.SizeType.FIXED
import java.awt.Dimension
import java.awt.Font
import java.awt.Font.BOLD
import java.awt.Font.ITALIC
import java.awt.Graphics2D
import java.awt.RenderingHints.KEY_TEXT_ANTIALIASING
import java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON
import kotlin.math.max

class ETitle(private val text: String, private val font: Int) : Element() {
    override val type: SizeType
        get() = FIXED

    override fun size(w: Dimension, g: Graphics2D): Size {
        val metrics = g.getFontMetrics(Font("TimesRoman", BOLD + ITALIC, font))
        return Size(metrics.stringWidth(text), metrics.height * (text.count { it == '\n' } + 1))
    }

    override fun paint(o: Offset, f: Size, w: Dimension, g: Graphics2D) {
        g.setRenderingHint(KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_ON)
        g.font = Font("TimesRoman", BOLD + ITALIC, font)
        val metrics = g.getFontMetrics(g.font)
        g.drawString(text, (w.width - metrics.stringWidth(text) + o.right - o.left) / 2,  max((metrics.height / 1.5).toInt() + o.up, o.up))
    }
}